package com.tusxapps.step_master.data.network.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileResponse(
    @SerialName("avatarLink")
    val avatarLink: String?,
    @SerialName("email")
    val email: String,
    @SerialName("fullname")
    val fullname: String,
    @SerialName("gender")
    val gender: String,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("rating")
    val rating: Rating?,
    @SerialName("region_id")
    val regionId: String,
    @SerialName("role")
    val role: String
) {
    @Serializable
    data class Rating(
        @SerialName("placeInCountry")
        val placeInCountry: String,
        @SerialName("placeInRegion")
        val placeInRegion: String
    )
}