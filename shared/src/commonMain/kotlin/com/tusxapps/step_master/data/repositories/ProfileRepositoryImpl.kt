package com.tusxapps.step_master.data.repositories

import com.tusxapps.step_master.Constants
import com.tusxapps.step_master.data.network.API
import com.tusxapps.step_master.data.toDomain
import com.tusxapps.step_master.domain.profile.Profile
import com.tusxapps.step_master.domain.profile.ProfileRepository
import com.tusxapps.step_master.domain.region.RegionRepository
import com.tusxapps.step_master.utils.FileStorage
import com.tusxapps.step_master.utils.suspendRunCatching
import io.ktor.utils.io.errors.IOException

class ProfileRepositoryImpl(
    private val api: API,
    private val fileStorage: FileStorage,
    private val regionRepository: RegionRepository
) : ProfileRepository {
    private var profile: Profile? = null
    private var avatar: ByteArray? = null
    override suspend fun getProfile(id: String?): Result<Profile> = suspendRunCatching {
        // todo use id in the future
        if (id == null) {
            fetchOwnProfile()
        } else {
            TODO("Implement with others id")
        }
    }

    private suspend fun fetchOwnProfile() = if (profile != null) {
        profile!!
    } else {
        val profileResponse = api.getProfile()

        if (!fileStorage.fileExists(Constants.AVATAR_FILE_NAME)) {
            profileResponse.avatarLink?.let {
                fileStorage.writeFile(Constants.AVATAR_FILE_NAME, api.downloadAvatar(it))
            }
        }

        val regionName = profileResponse.regionId.let {
            regionRepository.getRegionNameById(it).getOrThrow()
        }

        profileResponse.toDomain(regionName = regionName).also {
            profile = it
        }
    }

    override suspend fun updateProfile(
        fullName: String?,
        regionName: String?,
        nickname: String?
    ): Result<Unit> = suspendRunCatching {
        val regionId = regionName?.let { regionRepository.getRegionIdByName(it).getOrNull() }

        api.editProfile(
            fullName = fullName,
            regionId = regionId,
            nickname = nickname,
        ).also {
            profile = it.toDomain(
                regionName = regionName ?: profile?.regionName ?: ""
            )
        }
    }

    override suspend fun uploadAvatar(image: ByteArray): Result<Unit> = suspendRunCatching {
        api.uploadAvatar(image)
        fileStorage.writeFile(Constants.AVATAR_FILE_NAME, image)
        avatar = image
    }

    override suspend fun getAvatar(): Result<ByteArray> = suspendRunCatching {
        if (avatar != null) {
            avatar!!
        } else {
            fileStorage.readFile(Constants.AVATAR_FILE_NAME)?.also {
                avatar = it
            }
                ?: throw IOException("Avatar not found")
        }
    }

    override suspend fun editPassword(
        currentPassword: String,
        newPassword: String
    ) = suspendRunCatching {
        api.editPassword(
            oldPassword = currentPassword,
            newPassword = newPassword
        )
        Unit
    }
}