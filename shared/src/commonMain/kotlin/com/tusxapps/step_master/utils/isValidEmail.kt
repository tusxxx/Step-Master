package com.tusxapps.step_master.utils

fun String.isEmailValid(): Boolean {
    val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
    return this.matches(emailRegex)
}