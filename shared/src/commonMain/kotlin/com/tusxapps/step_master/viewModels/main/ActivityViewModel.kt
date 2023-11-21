package com.tusxapps.step_master.viewModels.main

import com.tusxapps.step_master.viewModels.base.BaseViewModel

class ActivityViewModel : BaseViewModel<ActivityViewModel.State>(State()) {


    data class State(
        val stepsCount: Int = 0,
        val goalStepsCount: Int = 12000,
        val activeTime: Int = 0,
        val goalActiveTime: Int = 120,
        val calories: Int = 0,
        val goalCalories: Int = 250,
        val dates: List<String> = emptyList()
    )
}