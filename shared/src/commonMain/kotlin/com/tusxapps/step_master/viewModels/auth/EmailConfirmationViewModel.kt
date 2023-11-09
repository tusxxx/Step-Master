package com.tusxapps.step_master.viewModels.auth

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.MutableStateFlow
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.tusxapps.step_master.domain.auth.AuthRepository
import com.tusxapps.step_master.utils.LCE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EmailConfirmationViewModel(
    private val authRepository: AuthRepository
) : KMMViewModel() {
    private val _state = MutableStateFlow(viewModelScope, State())
    val state = _state.asStateFlow()

    fun onFirstCodeNumberChange(codeNumber: String) {
        _state.update {
            it.copy(firstCodeNumber = codeNumber)
        }
    }

    fun onSecondCodeNumberChange(codeNumber: String) {
        _state.update {
            it.copy(secondCodeNumber = codeNumber)
        }
    }

    fun onThirdCodeNumberChange(codeNumber: String) {
        _state.update {
            it.copy(thirdCodeNumber = codeNumber)
        }
    }

    fun onFourthCodeNumberChange(codeNumber: String) {
        _state.update {
            it.copy(fourthCodeNumber = codeNumber)
        }
    }

    fun onFifthCodeNumberChange(codeNumber: String) {
        _state.update {
            it.copy(fifthCodeNumber = codeNumber)
        }
    }

    fun onRegisterClick(onSuccess: () -> Unit) {
        viewModelScope.coroutineScope.launch {
            _state.update { it.copy(lce = LCE.Loading) }

            with(state.value) {
                authRepository.register(
                    confirmCode = firstCodeNumber + secondCodeNumber + thirdCodeNumber + fourthCodeNumber + fifthCodeNumber
                ).onSuccess {
                    withContext(Dispatchers.Main) { onSuccess() }
                }.onFailure { error ->
                    _state.update { it.copy(lce = LCE.Error(error)) }
                    delay(3000)
                    _state.update { it.copy(lce = LCE.Idle) }
                }
            }
        }
    }

    data class State(
        val firstCodeNumber: String = "",
        val secondCodeNumber: String = "",
        val thirdCodeNumber: String = "",
        val fourthCodeNumber: String = "",
        val fifthCodeNumber: String = "",
        val lce: LCE = LCE.Idle
    )
}