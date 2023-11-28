package com.tusxapps.step_master.data.network.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DaysResponse(
    @SerialName("result")
    val result: List<Result>
) {
    @Serializable
    data class Result(
        @SerialName("calories")
        val calories: Float,
        @SerialName("date")
        val date: String,
        @SerialName("distance")
        val distance: Float,
        @SerialName("email")
        val email: String,
        @SerialName("_id")
        val id: String,
        @SerialName("plancalories")
        val plancalories: Float,
        @SerialName("plandistance")
        val plandistance: Float,
        @SerialName("plansteps")
        val plansteps: Int,
        @SerialName("steps")
        val steps: Int
    )
}