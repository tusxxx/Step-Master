package com.tusxapps.step_master.data.repositories

import com.tusxapps.step_master.data.network.API
import com.tusxapps.step_master.domain.AuthRepository
import com.tusxapps.step_master.domain.Gender

class AuthRepositoryImpl(
    private val api: API
) : AuthRepository {
    override suspend fun login(email: String, password: String): Result<Unit> = try {
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun register(
        email: String,
        nickname: String,
        fullName: String,
        region: String,
        gender: Gender,
        password: String
    ): Result<Unit> = try {
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun confirmEmail(): Result<Unit> = try {
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }
}