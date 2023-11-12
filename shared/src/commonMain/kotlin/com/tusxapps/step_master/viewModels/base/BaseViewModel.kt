package com.tusxapps.step_master.viewModels.base

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.MutableStateFlow
import com.tusxapps.step_master.utils.LCE
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

abstract class BaseViewModel<S>(initialState: S) : KMMViewModel() {
    protected val mutableState = MutableStateFlow(viewModelScope, initialState)
    val state = mutableState.asStateFlow()
    private val _lce: MutableStateFlow<LCE> = MutableStateFlow(viewModelScope, LCE.Idle)
    val lce = _lce.asStateFlow()

    suspend fun setError(throwable: Throwable, timeInMills: Long = 3000) {
        _lce.update { LCE.Error(throwable) }
        delay(timeInMills)
        _lce.update { LCE.Idle }
    }

    suspend fun load(block: suspend () -> Unit) {
        _lce.update { LCE.Loading }
        block()
        _lce.update { LCE.Idle }
    }

    @OptIn(ExperimentalContracts::class)
    inline fun withState(block: S.() -> Unit) {
        contract {
            callsInPlace(block, InvocationKind.EXACTLY_ONCE)
        }
        return state.value.block()
    }
}


