package com.tusxapps.step_master.data.network.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AchievablesResponse(
    @SerialName("achievements")
    val achievements: List<Achievement>,
    @SerialName("grades")
    val grades: List<Grade>
) {
    @Serializable
    data class Achievement(
        @SerialName("data")
        val `data`: List<Data>,
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String
    ) {
        @Serializable
        data class Data(
            @SerialName("groupId")
            val groupId: Int,
            @SerialName("id")
            val id: Int,
            @SerialName("link")
            val link: String,
            @SerialName("name")
            val name: String
        )
    }

    @Serializable
    data class Grade(
        @SerialName("data")
        val `data`: List<Data>,
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String
    ) {
        @Serializable
        data class Data(
            @SerialName("groupId")
            val groupId: Int,
            @SerialName("id")
            val id: Int,
            @SerialName("link")
            val link: String,
            @SerialName("name")
            val name: String
        )
    }
}