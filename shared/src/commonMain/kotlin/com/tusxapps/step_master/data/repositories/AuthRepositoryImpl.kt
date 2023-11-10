package com.tusxapps.step_master.data.repositories

import com.tusxapps.step_master.data.network.API
import com.tusxapps.step_master.domain.auth.AuthRepository
import com.tusxapps.step_master.domain.auth.UserData
import com.tusxapps.step_master.domain.exceptions.InvalidConfirmationCode
import com.tusxapps.step_master.domain.exceptions.RegionNotFoundException

class AuthRepositoryImpl(
    private val api: API
) : AuthRepository {
    private var confirmationCode: String? = null
    private var userData: UserData? = null
    private var regionId: String? = null

    override suspend fun login(email: String, password: String): Result<Unit> = try {
        // TODO use response
        val loginResponse = api.login(email, password)
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
            Result.success(Unit)
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun sendConfirmCode(userData: UserData): Result<Unit> = try {
        regionId = getRegionByName(userData.region) ?: throw RegionNotFoundException()

        val codeResponse = api.sendCodeToUser(userData.email)
        this.userData = userData
        confirmationCode = codeResponse.code
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    private suspend fun getRegionByName(userRegion: String): String? {
        val regions = api.getRegions().result
        return regions.firstOrNull {
            it.fullName.trim().lowercase() == userRegion.trim().lowercase()
        }?.id
    }
}