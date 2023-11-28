package com.tusxapps.step_master.data.network.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DayResponse(
    @SerialName("calories")
    val calories: Double,
    @SerialName("date")
    val date: String,
    @SerialName("distance")
    val distance: Double,
    @SerialName("email")
    val email: String,
    @SerialName("_id")
    val id: String,
    @SerialName("plancalories")
    val plancalories: Double,
    @SerialName("plandistance")
    val plandistance: Double,
    @SerialName("plansteps")
    val plansteps: Int,
    @SerialName("steps")
    val steps: Int
)