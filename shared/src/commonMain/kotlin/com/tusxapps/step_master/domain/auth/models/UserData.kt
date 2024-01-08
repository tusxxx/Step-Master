package com.tusxapps.step_master.domain.auth.models

import com.tusxapps.step_master.domain.Gender

data class UserData(
    val email: String,
    val nickname: String,
    val fullName: String,
    val region: String,
    val gender: Gender,
    val password: String
)