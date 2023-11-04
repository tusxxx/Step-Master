package com.tusxapps.step_master.android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.tusxapps.step_master.android.ui.theme.loadingBackgroundColor
import com.tusxapps.step_master.utils.LCE

@Composable
fun LCEView(lce: LCE, content: @Composable () -> Unit) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        content()
        if (lce == LCE.Loading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(loadingBackgroundColor)
                    .clickable(interactionSource, null, onClick = {})
            )
            CircularProgressIndicator()
        }
    }
}
