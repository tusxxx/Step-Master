package com.tusxapps.step_master.domain

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Unit>

    suspend fun register(
        email: String,
        nickname: String,
        fullName: String,
        region: String,
        gender: Gender,
        password: String
    ): Result<Unit>

    suspend fun confirmEmail(): Result<Unit>
}