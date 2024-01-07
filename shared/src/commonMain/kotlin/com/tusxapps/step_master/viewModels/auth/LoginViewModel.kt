package com.tusxapps.step_master.viewModels.auth

import com.rickclephas.kmm.viewmodel.coroutineScope
import com.tusxapps.step_master.domain.auth.AuthRepository
import com.tusxapps.step_master.domain.exceptions.EmptyFieldsException
import com.tusxapps.step_master.viewModels.base.BaseViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository
) : BaseViewModel<LoginViewModel.State>(State()) {
    fun onAuthFieldsChange(
        email: String = mutableState.value.email,
        password: String = mutableState.value.password
    ) {
        mutableState.update {
            it.copy(
                email = email,
                password = password,
            )
        }
    }

    fun onLoginClick(onSuccess: () -> Unit) {
        viewModelScope.coroutineScope.launch {
            load {
                withState {
                    if (email.isBlank() || password.isBlank()) {
                        setError(EmptyFieldsException())
                        return@load
                    }
                    authRepository.login(email, password)
                        .onSuccess {
                            onSuccess()
                        }
                        .onFailure { error ->
                            setError(error)
                        }
                }
            }
        }
    }

    data class State(
        val email: String = "",
        val password: String = "",
        val isPasswordVisible: Boolean = false
    )
}