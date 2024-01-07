package com.tusxapps.step_master.viewModels.main

import com.rickclephas.kmm.viewmodel.coroutineScope
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

    fun fetchProfile() {
        viewModelScope.coroutineScope.launch {
            profileRepository.getProfile()
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
                        )
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
        val clanUserCount: Int = 0
    )
}