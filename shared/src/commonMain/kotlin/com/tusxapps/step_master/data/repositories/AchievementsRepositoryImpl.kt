package com.tusxapps.step_master.data.repositories

import com.tusxapps.step_master.data.network.API
import com.tusxapps.step_master.data.network.models.AchievablesResponse
import com.tusxapps.step_master.data.toDomain
import com.tusxapps.step_master.domain.achievements.AchievementsRepository
import com.tusxapps.step_master.domain.achievements.models.Achievement
import com.tusxapps.step_master.domain.achievements.models.Rank
import com.tusxapps.step_master.utils.suspendRunCatching

class AchievementsRepositoryImpl(
    private val api: API
) : AchievementsRepository {
    private var achievables: AchievablesResponse? = null
    override suspend fun getAchievements(): Result<List<Achievement>> = suspendRunCatching {
        val achievables = achievables ?: api.getAchievables()

        achievables.achievements
            .flatMap { achievable ->
                achievable.data.map {
                    it.toDomain(achievable.name)
                }
            }
    }

    override suspend fun getRanks(): Result<List<Rank>> = suspendRunCatching {
        val achievables = achievables ?: api.getAchievables()

        achievables.grades
            .flatMap { achievable ->
                achievable.data.map {
                    it.toDomain(achievable.name)
                }
            }
    }
}
