package com.tusxapps.step_master.viewModels.main

import com.rickclephas.kmm.viewmodel.coroutineScope
import com.tusxapps.step_master.data.prefs.PreferencesStorage
import com.tusxapps.step_master.domain.days.DayRepository
import com.tusxapps.step_master.utils.minutesCountToHours
import com.tusxapps.step_master.utils.stepsCountToKilometers
import com.tusxapps.step_master.viewModels.base.BaseViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

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
                            it.copy(
                                stepsCount = today.steps,
                                goalStepsCount = preferencesStorage.goalSteps,
                                activeTime = preferencesStorage.todayActiveTime,
                                goalActiveTime = preferencesStorage.goalActiveTime,
                                calories = preferencesStorage.todayCalories,
                                goalCalories = preferencesStorage.goalCalories,
                                glassCount = preferencesStorage.glassCount,
                                bodyWeight = preferencesStorage.bodyWeight,
                                muscleWeight = preferencesStorage.muscleWeight,
                                bodyHeight = preferencesStorage.bodyWeight,
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