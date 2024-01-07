package com.tusxapps.step_master.data.network.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SelectedTitle(
    @SerialName("id")
    val id: Int,
    @SerialName("groupId")
    val groupId: Int,
    @SerialName("type")
    val type: String,
    @SerialName("name")
    val name: String
)