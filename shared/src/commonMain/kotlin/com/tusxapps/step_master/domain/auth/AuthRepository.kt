package com.tusxapps.step_master.domain.auth

import com.tusxapps.step_master.domain.auth.models.UserData

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Unit>

    suspend fun register(confirmCode: String): Result<Unit>

    suspend fun sendConfirmCode(userData: UserData): Result<Unit>

    suspend fun recoverPassword(email: String): Result<Unit>

    fun isAuthorized(): Boolean
}