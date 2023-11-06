package com.tusxapps.step_master.viewModels.auth

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EmailConfirmationViewModel : KMMViewModel() {
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

    data class State(
        val firstCodeNumber: String = "",
        val secondCodeNumber: String = "",
        val thirdCodeNumber: String = "",
        val fourthCodeNumber: String = "",
        val fifthCodeNumber: String = ""
    )
}