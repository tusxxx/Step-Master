package com.tusxapps.step_master.viewModels.auth

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.MutableStateFlow
import com.tusxapps.step_master.domain.Gender
import com.tusxapps.step_master.utils.Immutable
import com.tusxapps.step_master.utils.LCE
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel : KMMViewModel() {
    private val _state = MutableStateFlow(viewModelScope, State())
    val state = _state.asStateFlow()

    fun onRegisterFieldsChange(
        email: String = _state.value.email,
        nickname: String = _state.value.nickname,
        fullName: String = _state.value.fullName,
        region: String = _state.value.region,
        gender: Gender = _state.value.gender,
        password: String = _state.value.password,
        passwordRecovery: String = _state.value.passwordRecovery,
        isAgreedWithPolicy: Boolean = _state.value.isAgreedWithPolicy
    ) {
        _state.update {
            it.copy(
                email = email,
                nickname = nickname,
                fullName = fullName,
                region = region,
                gender = gender,
                password = password,
                passwordRecovery = passwordRecovery,
                isAgreedWithPolicy = isAgreedWithPolicy
            )
        }
    }

    @Immutable
    data class State(
        val lce: LCE = LCE.Idle,
        val email: String = "",
        val nickname: String = "",
        val fullName: String = "",
        val region: String = "",
        val gender: Gender = Gender.NONE,
        val password: String = "",
        val passwordRecovery: String = "",
        val isAgreedWithPolicy: Boolean = false
    )
}