package com.tusxapps.step_master.domain.achievements.models

interface Achievable {
    val name: String
    val type: String
    val isAchieved: Boolean
    val link: String
    val id: String
    val groupId: String
}