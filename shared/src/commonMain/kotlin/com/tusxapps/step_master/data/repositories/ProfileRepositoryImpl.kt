package com.tusxapps.step_master.data.repositories

import com.tusxapps.step_master.Constants
import com.tusxapps.step_master.data.network.API
import com.tusxapps.step_master.data.toDomain
import com.tusxapps.step_master.domain.achievements.AchievementsRepository
import com.tusxapps.step_master.domain.achievements.models.Achievable
import com.tusxapps.step_master.domain.achievements.models.Achievement
import com.tusxapps.step_master.domain.achievements.models.Rank
import com.tusxapps.step_master.domain.profile.ProfileRepository
import com.tusxapps.step_master.domain.profile.models.AchievementProgressData
import com.tusxapps.step_master.domain.profile.models.Profile
import com.tusxapps.step_master.domain.region.RegionRepository
import com.tusxapps.step_master.utils.FileStorage
import com.tusxapps.step_master.utils.suspendRunCatching
import io.ktor.utils.io.errors.IOException

class ProfileRepositoryImpl(
    private val api: API,
    private val fileStorage: FileStorage,
    private val regionRepository: RegionRepository,
    private val achievementsRepository: AchievementsRepository
) : ProfileRepository {
    private var profile: Profile? = null
    private var avatar: ByteArray? = null
    private var progress: AchievementProgressData? = null

    override suspend fun getProfile(id: String?, isForceToLoad: Boolean): Result<Profile> =
        suspendRunCatching {
            // todo use id in the future
            if (id == null) {
                fetchOwnProfile(isForceToLoad)
            } else {
                TODO("Implement with others id")
            }
        }

    private suspend fun fetchOwnProfile(isForceToLoad: Boolean) =
        if (profile != null && !isForceToLoad) {
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

            val allAchievables = achievementsRepository.getAchievements()
                .getOrDefault(emptyList()) + achievementsRepository.getRanks()
                .getOrDefault(emptyList())

            val profileGotIdsToGroupIds = profileResponse.titles.map {
                Triple(it.id.toString(), it.groupId.toString(), it.name)
            }
            val profilePinnedIdsToGroupIds = profileResponse.selectedTitles.map {
                Triple(it.id.toString(), it.groupId.toString(), it.name)
            }

            val gotAchievables = allAchievables.filter {
                Triple(it.id, it.groupId, it.name) in profileGotIdsToGroupIds
            }.map {
                when (it) {
                    is Rank -> {
                        it.copy(isAchieved = true)
                    }

                    is Achievement -> {
                        it.copy(isAchieved = true)
                    }

                    else -> {
                        it
                    }
                }
            }
            val pinnedAchievables = allAchievables.filter {
                Triple(it.id, it.groupId, it.name) in profilePinnedIdsToGroupIds && it is Achievement
            }


            profileResponse.toDomain(
                regionName = regionName,
                achievables = gotAchievables,
                pinnedAchievables = pinnedAchievables
            ).also {
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

    override suspend fun pinAchievable(achievable: Achievable) = suspendRunCatching {
        api.addSelectedTitle(
            id = achievable.id.toInt(),
            groupId = achievable.groupId.toInt(),
            name = achievable.type
        )
        Unit
    }

    override suspend fun getProgress(): Result<AchievementProgressData> = suspendRunCatching {
        progress ?: fetchProgress()
    }

    private suspend fun fetchProgress(): AchievementProgressData {
        val allAchievables = achievementsRepository.getAchievements().getOrDefault(emptyList())
        val currentTitleResponse = api.getTitleProgress()

        val currentAchievableIndex = allAchievables.indexOfFirst {
            val idToGroupId = it.id to it.groupId
            val currentTitleIdToGroupId =
                currentTitleResponse.title.id.toString() to currentTitleResponse.title.groupId.toString()
            idToGroupId == currentTitleIdToGroupId
        }

        return AchievementProgressData(
            name = allAchievables[currentAchievableIndex].name,
            prevIcon = allAchievables[currentAchievableIndex].link,
            nextIcon = allAchievables.getOrElse(currentAchievableIndex + 1) { allAchievables[currentAchievableIndex] }.link,
            dealtKm = currentTitleResponse.kmDealt,
            neededKm = currentTitleResponse.kmNeeded
        ).also {
            progress = it
        }
    }
}