package com.tusxapps.step_master.data.network.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("email")
    val email: String,
    @SerialName("fullname")
    val fullname: String,
    @SerialName("gender")
    val gender: String,
    @SerialName("_id")
    val id: String,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("password")
    val password: String,
    @SerialName("region_id")
    val regionId: String,
    @SerialName("role")
    val role: String,
    @SerialName("lastCookie")
    val lastCookie: String
)