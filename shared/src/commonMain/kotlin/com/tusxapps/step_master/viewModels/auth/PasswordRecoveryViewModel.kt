package com.tusxapps.step_master.viewModels.auth

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.MutableStateFlow
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.tusxapps.step_master.utils.Immutable
import com.tusxapps.step_master.utils.LCE
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PasswordRecoveryViewModel : KMMViewModel() {
    private val _state = MutableStateFlow(viewModelScope, State())
    val state = _state.asStateFlow()

    fun onEmailChange(email: String) {
        _state.update {
            it.copy(email = email)
        }
    }

    fun onRecoveryClick() {
        viewModelScope.coroutineScope.launch {
            _state.update {
                it.copy(lce = LCE.Loading)
            }
            delay(1000)
            _state.update {
                it.copy(isEmailSent = true, lce = LCE.Success(Unit))
            }
        }
    }

    @Immutable
    data class State(
        val email: String = "",
        val isEmailSent: Boolean = false,
        val lce: LCE = LCE.Idle
    )
}