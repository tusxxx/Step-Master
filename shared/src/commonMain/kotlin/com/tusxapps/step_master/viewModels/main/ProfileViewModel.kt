package com.tusxapps.step_master.viewModels.main

import com.tusxapps.step_master.viewModels.base.BaseViewModel

class ProfileViewModel() : BaseViewModel<ProfileViewModel.State>(State()) {



    data class State(
        val nickname: String = "",
        val name: String = "",
        val profilePictureUrl: String = "",
        val userRegionPlace: Int = 0,
        val regionUserCount: Int = 0,
        val userCountyPlace: Int = 0,
        val countryUserCount: Int = 0,
        val userClanPlace: Int = 0,
        val clanUserCount: Int = 0
    )
}