package com.tusxapps.step_master.android.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import cafe.adriel.voyager.androidx.AndroidScreen

abstract class BottomBarScreen : AndroidScreen() {
    abstract val options: BottomBarScreenOptions
        @Composable
        get
}

data class BottomBarScreenOptions(
    val name: String,
    val icon: Painter
)