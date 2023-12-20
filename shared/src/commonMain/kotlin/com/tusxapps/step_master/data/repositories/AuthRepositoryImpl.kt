package com.tusxapps.step_master.data.repositories

import com.tusxapps.step_master.data.network.API
import com.tusxapps.step_master.data.prefs.PreferencesStorage
import com.tusxapps.step_master.domain.auth.AuthRepository
import com.tusxapps.step_master.domain.auth.UserData
import com.tusxapps.step_master.domain.exceptions.CantRecoverPasswordException
import com.tusxapps.step_master.domain.exceptions.InvalidConfirmationCode
import com.tusxapps.step_master.domain.exceptions.RegionNotFoundException
import com.tusxapps.step_master.utils.suspendRunCatching
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess

class AuthRepositoryImpl(
    private val api: API,
    private val preferencesStorage: PreferencesStorage
) : AuthRepository {
    private var confirmationCode: String? = null
    private var userData: UserData? = null
    private var regionId: String? = null

    override suspend fun login(email: String, password: String): Result<Unit> = try {
        val loginResponse = api.login(email, password)
        if (loginResponse.status.isSuccess()) {
            preferencesStorage.isAuthorized = true
            Result.success(Unit)
        } else {
            Result.failure(IllegalArgumentException("Login failed"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun register(
        confirmCode: String
    ): Result<Unit> = try {
        if (confirmCode != confirmationCode) {
            throw InvalidConfirmationCode()
        }

        val userData = checkNotNull(this.userData)
        val regionId = checkNotNull(this.regionId)

        userData.let {
            api.register(
                email = it.email,
                nickname = it.nickname,
                fullName = it.fullName,
                regionId = regionId,
                gender = it.gender.name.lowercase(),
                password = it.password
            )
            preferencesStorage.isAuthorized = true
            Result.success(Unit)
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun sendConfirmCode(userData: UserData): Result<Unit> = try {
        regionId = getRegionByName(userData.region) ?: throw RegionNotFoundException()

        val codeResponse = api.sendCodeToUser(userData.email.trim())
        this.userData = userData
        confirmationCode = codeResponse.code
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun recoverPassword(email: String): Result<Unit> = suspendRunCatching {
        val response = api.recoverPassword(email)
        if (response.status != HttpStatusCode.OK)
            throw CantRecoverPasswordException()
    }

    override fun isAuthorized(): Boolean = preferencesStorage.isAuthorized

    private suspend fun getRegionByName(userRegion: String): String? {
        val regions = api.getRegions().result
        return regions.firstOrNull {
            it.fullName.trim().lowercase() == userRegion.trim().lowercase()
        }?.id
    }
}