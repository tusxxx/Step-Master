package com.tusxapps.step_master.domain.achievements

import com.tusxapps.step_master.domain.achievements.models.Achievement
import com.tusxapps.step_master.domain.achievements.models.Rank

interface AchievementsRepository {
    suspend fun getAchievements(): Result<List<Achievement>>
    suspend fun getRanks(): Result<List<Rank>>
}