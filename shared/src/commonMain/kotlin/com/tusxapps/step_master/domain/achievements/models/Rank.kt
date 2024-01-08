package com.tusxapps.step_master.domain.achievements.models

data class Rank(
    override val name: String,
    override val type: String,
    override val isAchieved: Boolean,
    override val link: String,
    override val id: String,
    override val groupId: String
) : Achievable

