package com.tusxapps.step_master.utils

@Immutable
sealed interface LCE {
    data object Idle : LCE
    data object Loading : LCE
    data class Success<T>(val data: T) : LCE
    data class Error(val message: String) : LCE
}
