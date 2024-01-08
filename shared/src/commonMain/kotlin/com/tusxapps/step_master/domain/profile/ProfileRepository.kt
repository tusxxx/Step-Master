package com.tusxapps.step_master.domain.profile

import com.tusxapps.step_master.domain.achievements.models.Achievable
import com.tusxapps.step_master.domain.profile.models.AchievementProgressData
import com.tusxapps.step_master.domain.profile.models.Profile

interface ProfileRepository {
    suspend fun getProfile(id: String? = null, isForceToLoad: Boolean = false): Result<Profile>
    suspend fun updateProfile(
        fullName: String? = null,
        regionName: String? = null,
        nickname: String? = null
    ): Result<Unit>

    suspend fun uploadAvatar(image: ByteArray): Result<Unit>
    suspend fun getAvatar(): Result<ByteArray>
    suspend fun editPassword(currentPassword: String, newPassword: String): Result<Unit>
    suspend fun pinAchievable(achievable: Achievable): Result<Unit>

    suspend fun getProgress(): Result<AchievementProgressData>
}