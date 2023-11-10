package com.tusxapps.step_master.data.network.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegionsResponse(
    @SerialName("result")
    val result: List<Result>
) {
    @Serializable
    data class Result(
        @SerialName("fullName")
        val fullName: String,
        @SerialName("_id")
        val id: String
    )
}