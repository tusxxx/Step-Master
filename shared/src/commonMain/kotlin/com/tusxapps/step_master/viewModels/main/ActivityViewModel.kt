package com.tusxapps.step_master.viewModels.main

import com.rickclephas.kmm.viewmodel.coroutineScope
import com.tusxapps.step_master.domain.days.DayRepository
import com.tusxapps.step_master.domain.days.models.DayInfo
import com.tusxapps.step_master.viewModels.base.BaseViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ActivityViewModel(
    private val dayRepository: DayRepository
) : BaseViewModel<ActivityViewModel.State>(State()) {
    init {
        viewModelScope.coroutineScope.launch {
            load {
                dayRepository.getDaysForWeek()
                    .onSuccess { days ->
                        mutableState.update {
                            it.copy(
                                days = days,
                                currentDay = dayRepository.getToday().getOrNull()
                            )
                        }
                    }.onFailure {
                        setError(it)
                    }

                dayRepository.getDays()
                    .onSuccess { days ->
                        mutableState.update {
                            it.copy(
                                existingDays = days
                            )
                        }
                    }
            }
        }
    }


    data class State(
        val days: List<DayInfo> = listOf(),
        val currentDay: DayInfo? = null,
        val existingDays: List<DayInfo> = listOf()
    )
}