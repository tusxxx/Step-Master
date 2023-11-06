package com.tusxapps.step_master.viewModels.auth

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.MutableStateFlow
import com.rickclephas.kmm.viewmodel.coroutineScope
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EmailConfirmationViewModel : KMMViewModel() {
    private val _state = MutableStateFlow(viewModelScope, State())
    val state = _state.asStateFlow()

    fun onFirstCodeNumberChange(codeNumber: String) {
        _state.update {
            it.copy(firstCodeNumber = codeNumber)
        }
        Napier.d {
            "State: ${_state.value}"
        }
    }

    fun onSecondCodeNumberChange(codeNumber: String) {
        _state.update {
            it.copy(secondCodeNumber = codeNumber)
        }
        Napier.d {
            "State: ${_state.value}"
        }
    }

    fun onThirdCodeNumberChange(codeNumber: String) {
        _state.update {
            it.copy(thirdCodeNumber = codeNumber)
        }
        Napier.d {
            "State: ${_state.value}"
        }
    }

    fun onFourthCodeNumberChange(codeNumber: String) {
        _state.update {
            it.copy(fourthCodeNumber = codeNumber)
        }
        Napier.d {
            "State: ${_state.value}"
        }
    }

    fun onFifthCodeNumberChange(codeNumber: String) {
        _state.update {
            it.copy(fifthCodeNumber = codeNumber)
        }
        Napier.d {
            "State: ${_state.value}"
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