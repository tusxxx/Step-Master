package com.tusxapps.step_master.data.network.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TitleProgressResponse(
    @SerialName("km_dealt")
    val kmDealt: Int,
    @SerialName("km_needed")
    val kmNeeded: Int,
    @SerialName("title")
    val title: Title
) {
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