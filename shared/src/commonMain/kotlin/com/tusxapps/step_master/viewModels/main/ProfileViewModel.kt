package com.tusxapps.step_master.viewModels.main

import com.rickclephas.kmm.viewmodel.coroutineScope
import com.tusxapps.step_master.domain.achievements.models.Achievable
import com.tusxapps.step_master.domain.profile.ProfileRepository
import com.tusxapps.step_master.utils.Immutable
import com.tusxapps.step_master.viewModels.base.BaseViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val profileRepository: ProfileRepository
) : BaseViewModel<ProfileViewModel.State>(State()) {
    init {
        fetchProfile()
    }

    fun fetchProfile(isForceLoad: Boolean = false) {
        viewModelScope.coroutineScope.launch {
            load {
                profileRepository.getProfile(isForceToLoad = isForceLoad)
                    .onSuccess { profile ->
                        mutableState.update {
                            it.copy(
                                nickname = profile.nickname,
                                name = profile.fullName,
                                avatar = profileRepository.getAvatar().getOrNull(),
                                userRegionPlace = profile.placeInRegion,
                                regionUserCount = profile.totalPlacesInRegion,
                                userCountyPlace = profile.placeInCountry,
                                countryUserCount = profile.totalPlacesInCountry,
                                achievables = profile.pinnedAchievables.takeLast(3)
                            )
                        }
                    }

                profileRepository.getProgress().onSuccess { progress ->
                    mutableState.update {
                        it.copy(
                            dealtKm = progress.dealtKm,
                            neededKm = progress.neededKm,
                            achievementName = progress.name,
                            achievementPrevIcon = progress.prevIcon,
                            achievementNextIcon = progress.nextIcon,
                        )
                    }
                }
            }
        }
    }


    @Immutable
    data class State(
        val nickname: String = "",
        val name: String = "",
        val avatar: ByteArray? = null,
        val userRegionPlace: Int = 0,
        val regionUserCount: Int = 0,
        val userCountyPlace: Int = 0,
        val countryUserCount: Int = 0,
        val userClanPlace: Int = 0,
        val clanUserCount: Int = 0,
        val achievables: List<Achievable> = listOf(
//            Achievement("test", "test", true, "https://sterpmaster.obs.ru-moscow-1.hc.sbercloud.ru/StepMaster/Titles/Achievements/1%40%D0%A8%D0%B0%D0%B3%D0%B8%20%D0%B7%D0%B0%20%D0%B4%D0%B5%D0%BD%D1%8C/1%4010%20000.svg?AWSAccessKeyId=L7TEHWVRWAINB9YN1LQK&Expires=1704618329&Signature=KDiqCcO1b5lZMdLSEMVEs0%2BtVnY%3D"),
//            Achievement("tes2t", "test2", true, "https://sterpmaster.obs.ru-moscow-1.hc.sbercloud.ru/StepMaster/Titles/Achievements/1%40%D0%A8%D0%B0%D0%B3%D0%B8%20%D0%B7%D0%B0%20%D0%B4%D0%B5%D0%BD%D1%8C/1%4010%20000.svg?AWSAccessKeyId=L7TEHWVRWAINB9YN1LQK&Expires=1704618329&Signature=KDiqCcO1b5lZMdLSEMVEs0%2BtVnY%3D"),
//            Achievement("test2", "test2", true, "https://sterpmaster.obs.ru-moscow-1.hc.sbercloud.ru/StepMaster/Titles/Achievements/1%40%D0%A8%D0%B0%D0%B3%D0%B8%20%D0%B7%D0%B0%20%D0%B4%D0%B5%D0%BD%D1%8C/1%4010%20000.svg?AWSAccessKeyId=L7TEHWVRWAINB9YN1LQK&Expires=1704618329&Signature=KDiqCcO1b5lZMdLSEMVEs0%2BtVnY%3D"),
        ),
        val dealtKm: Int = 0,
        val neededKm: Int = 1,
        val achievementName: String = "",
        val achievementPrevIcon: String = "",
        val achievementNextIcon: String = "",
    )
}