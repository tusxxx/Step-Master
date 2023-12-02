package com.tusxapps.step_master.viewModels.main

import com.rickclephas.kmm.viewmodel.coroutineScope
import com.tusxapps.step_master.data.prefs.PreferencesStorage
import com.tusxapps.step_master.domain.days.DayRepository
import com.tusxapps.step_master.utils.countCalories
import com.tusxapps.step_master.utils.minutesCountToHours
import com.tusxapps.step_master.utils.stepsCountToKilometers
import com.tusxapps.step_master.viewModels.base.BaseViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class SummaryViewModel(
    private val dayRepository: DayRepository,
    private val preferencesStorage: PreferencesStorage
) : BaseViewModel<SummaryViewModel.State>(State()) {

    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.coroutineScope.launch {
            load {
                dayRepository.getToday().onSuccess { today ->
                    withState {
                        mutableState.update {
                            val weight = preferencesStorage.bodyWeight
                            val activeTime = preferencesStorage.todayActiveTime

                            it.copy(
                                stepsCount = today.steps,
                                goalStepsCount = preferencesStorage.goalSteps,
                                activeTime = activeTime,
                                goalActiveTime = preferencesStorage.goalActiveTime,
                                calories = setCalories(weight, activeTime),
                                goalCalories = preferencesStorage.goalCalories,
                                glassCount = preferencesStorage.glassCount,
                                bodyWeight = preferencesStorage.bodyWeight,
                                muscleWeight = preferencesStorage.muscleWeight,
                                bodyHeight = weight,
                                bodyFat = preferencesStorage.bodyFat,
                                distance = setDistance(),
                                activeTimeHours = setActiveTimeHours()
                            )
                        }
                    }
                }
            }
        }
    }

    fun onGoalStepsCountChange(goalStepsCount: Int) {
        preferencesStorage.goalSteps = goalStepsCount
        mutableState.update { it.copy(goalStepsCount = goalStepsCount) }
    }

    fun onBodyCompositionChange(
        bodyWeight: Float? = state.value.bodyWeight,
        muscleWeight: Float? = state.value.muscleWeight,
        bodyHeight: Float? = state.value.bodyHeight,
        bodyFat: Float? = state.value.bodyFat
    ) {
        with(preferencesStorage) {
            bodyWeight?.let {
                this.bodyWeight = it
            }
            muscleWeight?.let {
                this.muscleWeight = it
            }
            bodyHeight?.let {
                this.bodyHeight = it
            }
            bodyFat?.let {
                this.bodyFat = it
            }
        }
        mutableState.update {
            it.copy(
                bodyWeight = bodyWeight,
                muscleWeight = muscleWeight,
                bodyHeight = bodyHeight,
                bodyFat = bodyFat
            )
        }
    }

    fun addGlass() {
        preferencesStorage.glassCount += 1
        mutableState.update { it.copy(glassCount = it.glassCount + 1) }
    }

    fun removeGlass() {
        preferencesStorage.glassCount -= 1
        mutableState.update { it.copy(glassCount = it.glassCount - 1) }
    }

    private fun setDistance(): Float = if (state.value.stepsCount != 0) {
        stepsCountToKilometers(state.value.stepsCount)
    } else {
        0f
    }

    private fun setActiveTimeHours(): Float = if (state.value.activeTime != 0) {
        minutesCountToHours(state.value.activeTime)
    } else {
        0f
    }

    private fun setCalories(bodyWeight: Float?, activeTime: Int): Int? =
        if (bodyWeight != null) {
            countCalories(
                activeTime = minutesCountToHours(activeTime),
                weightKgs = bodyWeight
            ).roundToInt()
        } else {
            null
        }

    data class State(
        val stepsCount: Int = 0,
        val goalStepsCount: Int = 0,
        val activeTime: Int = 0,
        val goalActiveTime: Int = 120,
        val calories: Int? = null,
        val goalCalories: Int = 250,
        val glassCount: Int = 0,
        val bodyWeight: Float? = null,
        val muscleWeight: Float? = null,
        val bodyHeight: Float? = null,
        val bodyFat: Float? = null,
        val distance: Float = 0f,
        val activeTimeHours: Float = 0f
    )
}