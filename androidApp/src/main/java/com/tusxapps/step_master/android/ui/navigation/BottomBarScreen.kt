package com.tusxapps.step_master.android.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.core.screen.ScreenKey

abstract class BottomBarScreen : AndroidScreen() {
    abstract val options: BottomBarScreenOptions
        @Composable
        get

    override val key: ScreenKey
        get() = this::class.java.name
}

data class BottomBarScreenOptions(
    val name: String,
    val icon: Painter
)