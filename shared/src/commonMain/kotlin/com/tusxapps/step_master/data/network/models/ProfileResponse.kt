package com.tusxapps.step_master.data.network.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileResponse(
    @SerialName("avatarLink")
    val avatarLink: String,
    @SerialName("email")
    val email: String,
    @SerialName("fullname")
    val fullname: String,
    @SerialName("gender")
    val gender: String,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("rating")
    val rating: Rating,
    @SerialName("region_id")
    val regionId: String,
    @SerialName("role")
    val role: String,
    @SerialName("selectedTitles")
    val selectedTitles: List<SelectedTitle>,
    @SerialName("titles")
    val titles: List<Title>,
    @SerialName("vipStatus")
    val vipStatus: Boolean
) {
    @Serializable
    data class Rating(
        @SerialName("placeInCountry")
        val placeInCountry: String,
        @SerialName("placeInRegion")
        val placeInRegion: String
    )

    @Serializable
    data class SelectedTitle(
        @SerialName("groupId")
        val groupId: Int,
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String,
        @SerialName("type")
        val type: String
    )

    @Serializable
    data class Title(
        @SerialName("groupId")
        val groupId: Int,
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String,
        @SerialName("type")
        val type: String
    )
}