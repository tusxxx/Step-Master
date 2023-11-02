package com.tusxapps.step_master.viewModels.auth

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.MutableStateFlow
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : KMMViewModel(

) {
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

    fun onLoginClick(onSuccess: () -> Unit, onError: (String) -> Unit = {}) {
        Napier.d { "onLoginClick" }
        onSuccess()
    }

    data class State(
        val email: String = "",
        val password: String = "",
        val isPasswordVisible: Boolean = false
    )
}