package com.tusxapps.step_master

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform