package com.tusxapps.step_master.viewModels.main

import com.tusxapps.step_master.viewModels.base.BaseViewModel
import kotlinx.coroutines.flow.update

class SummaryViewModel() : BaseViewModel<SummaryViewModel.State>(State()) {
    fun onGoalStepsCountChange(goalStepsCount: Int) {
        mutableState.update { it.copy(goalStepsCount = goalStepsCount) }
    }

    fun onBodyCompositionChange(
        bodyWeight: Float? = state.value.bodyWeight,
        muscleWeight: Float? = state.value.muscleWeight,
        bodyHeight: Float? = state.value.bodyHeight,
        bodyFat: Float? = state.value.bodyFat
    ) {
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
        mutableState.update { it.copy(glassCount = it.glassCount + 1) }
    }

    fun removeGlass() {
        mutableState.update { it.copy(glassCount = it.glassCount - 1) }
    }

    data class State(
        val stepsCount: Int = 0,
        val goalStepsCount: Int = 12000,
        val activeTime: Int = 0,
        val goalActiveTime: Int = 120,
        val calories: Int = 0,
        val goalCalories: Int = 250,
        val glassCount: Int = 0,
        val bodyWeight: Float? = null,
        val muscleWeight: Float? = null,
        val bodyHeight: Float? = null,
        val bodyFat: Float? = null
    )
}