package com.tusxapps.step_master.viewModels.auth

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.MutableStateFlow
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.tusxapps.step_master.domain.auth.AuthRepository
import com.tusxapps.step_master.domain.exceptions.EmptyFieldsException
import com.tusxapps.step_master.utils.LCE
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository
) : KMMViewModel() {
    private val _state = MutableStateFlow(viewModelScope, State())
    val state = _state.asStateFlow()

    fun onAuthFieldsChange(
        email: String = _state.value.email,
        password: String = _state.value.password
    ) {
        _state.update {
            it.copy(
                email = email,
                password = password,
            )
        }
    }

    fun onLoginClick(onSuccess: () -> Unit) {
        viewModelScope.coroutineScope.launch {
            with(state.value) {
                if (email.isBlank() || password.isBlank()) {
                    _state.update { it.copy(lce = LCE.Error(EmptyFieldsException())) }
                    delay(3000)
                    _state.update { it.copy(lce = LCE.Idle) }
                    return@launch
                }
                _state.update { it.copy(lce = LCE.Loading) }
                authRepository.login(email, password)
                    .onSuccess {
                        _state.update { it.copy(lce = LCE.Idle) }
                        onSuccess()
                    }
                    .onFailure { error ->
                        _state.update { it.copy(lce = LCE.Error(error)) }
                        delay(3000)
                        _state.update { it.copy(lce = LCE.Idle) }
                    }
            }
        }
    }

    data class State(
        val email: String = "",
        val password: String = "",
        val isPasswordVisible: Boolean = false,
        val lce: LCE = LCE.Idle
    )
}