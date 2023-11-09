package com.tusxapps.step_master.domain.auth

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Unit>

    suspend fun register(confirmCode: String): Result<Unit>

    suspend fun sendConfirmCode(userData: UserData): Result<Unit>
}