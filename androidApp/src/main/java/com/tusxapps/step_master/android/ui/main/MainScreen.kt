package com.tusxapps.step_master.android.ui.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.androidx.AndroidScreen

object MainScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        Text(text = "main screen")
    }
}