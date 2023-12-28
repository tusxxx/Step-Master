package com.tusxapps.step_master.viewModels.main

import com.rickclephas.kmm.viewmodel.coroutineScope
import com.tusxapps.step_master.domain.profile.ProfileRepository
import com.tusxapps.step_master.utils.Immutable
import com.tusxapps.step_master.viewModels.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val profileRepository: ProfileRepository
) : BaseViewModel<SettingsViewModel.State>(State()) {

    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.coroutineScope.launch {
            load {
                profileRepository.getProfile()
                    .onSuccess { profile ->
                        mutableState.update {
                            it.copy(
                                nickname = profile.nickname,
                                name = profile.fullName,
                                profilePictureUrl = profileRepository.getAvatar().getOrNull(),
                                regionName = profile.regionName
                            )
                        }
                    }
                    .onFailure {
                        setError(it)
                    }
            }
        }
    }

    fun onPhotoPick(byteArray: ByteArray) {
        viewModelScope.coroutineScope.launch(Dispatchers.IO) {
            mutableState.update { it.copy(isUploadingImage = true) }
            profileRepository.uploadAvatar(byteArray)
            mutableState.update {
                it.copy(profilePictureUrl = profileRepository.getAvatar().getOrNull())
            }
            mutableState.update { it.copy(isUploadingImage = false) }
        }
    }

    fun onSaveClick() {
        viewModelScope.coroutineScope.launch(Dispatchers.IO) {
            load {
                withState {
                    if (newPassword != confirmPassword) {
                        setError(IllegalStateException("Passwords don't match"))
                        return@load
                    }
                    profileRepository.updateProfile(
                        fullName = name,
                        nickname = nickname,
                        regionName = regionName
                    )
                    if (currentPassword.isNotBlank() && newPassword.isNotBlank())
                        profileRepository.editPassword(currentPassword, newPassword).onFailure {
                            setError(it)
                            return@load
                        }
                    changeEditMode()
                }
            }
        }
    }

    fun onNameChange(name: String) {
        mutableState.update { it.copy(name = name) }
    }

    fun onNicknameChange(nickname: String) {
        mutableState.update { it.copy(nickname = nickname) }
    }

    fun onNewPasswordChange(newPassword: String) {
        mutableState.update { it.copy(newPassword = newPassword) }
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        mutableState.update { it.copy(confirmPassword = confirmPassword) }
    }

    fun onCurrentPasswordChange(currentPassword: String) {
        mutableState.update { it.copy(currentPassword = currentPassword) }
    }

    fun changeEditMode() {
        mutableState.update { it.copy(isEditMode = !it.isEditMode) }
    }

    @Immutable
    data class State(
        val nickname: String = "",
        val name: String = "",
        val profilePictureUrl: ByteArray? = null,
        val regionName: String = "",
        val currentPassword: String = "",
        val newPassword: String = "",
        val confirmPassword: String = "",
        val isUploadingImage: Boolean = false,
        val isEditMode: Boolean = false
    )
}