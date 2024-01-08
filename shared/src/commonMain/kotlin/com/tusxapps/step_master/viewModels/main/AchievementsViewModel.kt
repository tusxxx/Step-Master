package com.tusxapps.step_master.viewModels.main

import com.rickclephas.kmm.viewmodel.coroutineScope
import com.tusxapps.step_master.domain.achievements.AchievementsRepository
import com.tusxapps.step_master.domain.achievements.models.Achievable
import com.tusxapps.step_master.domain.achievements.models.Achievement
import com.tusxapps.step_master.domain.achievements.models.Rank
import com.tusxapps.step_master.domain.profile.ProfileRepository
import com.tusxapps.step_master.viewModels.base.BaseViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AchievementsViewModel(
    private val achievementsRepo: AchievementsRepository,
    private val profileRepo: ProfileRepository
) : BaseViewModel<AchievementsViewModel.State>(State()) {
    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.coroutineScope.launch {
            achievementsRepo.getRanks().onSuccess { ranks ->
                mutableState.update { it.copy(ranks = ranks) }
            }
            achievementsRepo.getAchievements().onSuccess { achievements ->
                mutableState.update { it.copy(achievements = achievements) }
            }
            profileRepo.getProfile().onSuccess { profile ->
                val ranks = profile.achievables.filterIsInstance<Rank>()
                val achievements = profile.achievables.filterIsInstance<Achievement>()

                mutableState.update {
                    val tempAchievements = it.achievements.map {
                        if (it.id to it.groupId in achievements.map { it.id to it.groupId })
                            it.copy(isAchieved = true)
                        else
                            it
                    }

                    val tempRanks = it.ranks.map {
                        if (it.id to it.groupId in ranks.map { it.id to it.groupId })
                            it.copy(isAchieved = true)
                        else
                            it
                    }

                    it.copy(achievements = tempAchievements, ranks = tempRanks)
                }
            }
        }
    }

    fun pinAchievable(achievable: Achievable) {
        viewModelScope.coroutineScope.launch {
            profileRepo.pinAchievable(achievable)
        }
    }

    data class State(
        val achievements: List<Achievement> = emptyList(),
        val ranks: List<Rank> = emptyList()
    )
}