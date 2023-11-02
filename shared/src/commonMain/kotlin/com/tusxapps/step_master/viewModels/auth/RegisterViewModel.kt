package com.tusxapps.step_master.viewModels.auth

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.MutableStateFlow
import com.tusxapps.step_master.domain.Gender
import com.tusxapps.step_master.utils.LCE
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel : KMMViewModel() {
    private val _state = MutableStateFlow(viewModelScope, State())
    val state = _state.asStateFlow()

    fun onRegisterFieldsChange(newRegisterFieldsState: State.RegisterFields) {
        _state.update {
            it.copy(registerFields = newRegisterFieldsState)
        }
    }

    data class State(
        val lce: LCE = LCE.Idle,
        val registerFields: RegisterFields = RegisterFields()
    ) {
        data class RegisterFields(
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
}