package com.tusxapps.step_master.domain.profile

interface ProfileRepository {
    suspend fun getProfile(id: String? = null): Result<Profile>
    suspend fun updateProfile(
        fullName: String? = null,
        regionName: String? = null,
        nickname: String? = null
    ): Result<Unit>

    suspend fun uploadAvatar(image: ByteArray): Result<Unit>
    suspend fun getAvatar(): Result<ByteArray>
    suspend fun editPassword(currentPassword: String, newPassword: String): Result<Unit>
}