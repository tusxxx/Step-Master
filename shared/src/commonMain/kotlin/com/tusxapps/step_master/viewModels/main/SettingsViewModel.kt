package com.tusxapps.step_master.viewModels.main

import com.tusxapps.step_master.viewModels.base.BaseViewModel

class SettingsViewModel : BaseViewModel<SettingsViewModel.State>(State()) {

    data class State(
        val nickname: String = "",
        val name: String = "",
        val profilePictureUrl: String = "",
        val regionName: String = "",
        val currentPassword: String = "",
        val newPassword: String = "",
        val confirmPassword: String = ""
    )
}