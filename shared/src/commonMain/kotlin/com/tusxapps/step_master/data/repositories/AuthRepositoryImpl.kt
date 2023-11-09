package com.tusxapps.step_master.data.repositories

import com.tusxapps.step_master.data.network.API
import com.tusxapps.step_master.domain.auth.AuthRepository
import com.tusxapps.step_master.domain.auth.UserData
import com.tusxapps.step_master.domain.exceptions.InvalidConfirmationCode
import io.github.aakira.napier.Napier

class AuthRepositoryImpl(
    private val api: API
) : AuthRepository {
    private var confirmationCode: String? = null
    private var userData: UserData? = null

    override suspend fun login(email: String, password: String): Result<Unit> = try {
        // todo
        api.login(email, password).let {
            Napier.d { it.toString() }
            error(it)
        }
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun register(
        confirmCode: String
    ): Result<Unit> = try {
        if (confirmCode != confirmationCode) {
           throw InvalidConfirmationCode()
        }

        // TODO get regionId
        val regionId = "asdasddd"

        userData?.let {
            api.register(
                email = it.email,
                nickname = it.nickname,
                fullName = it.fullName,
                regionId = regionId,
                gender = it.gender.name.lowercase(),
                password = it.password
            )
            Result.success(Unit)
        } ?: throw NullPointerException("userData is null")
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun sendConfirmCode(userData: UserData): Result<Unit> = try {
        val code = api.sendCodeToUser(userData.email)
        this.userData = userData
        confirmationCode = code
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }
}