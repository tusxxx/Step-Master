package com.tusxapps.step_master.android.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.tusxapps.step_master.android.ui.theme.largeDp
import com.tusxapps.step_master.android.ui.theme.loadingBackgroundColor
import com.tusxapps.step_master.utils.LCE

@Composable
fun LCEView(lce: LCE, content: @Composable () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        content()
        if (lce == LCE.Loading) {
            LoadingBox()
        }
        AnimatedVisibility(
            visible = lce is LCE.Error,
            enter =  slideInVertically { fullHeight -> fullHeight / 2 },
            exit = slideOutVertically { fullHeight -> fullHeight / 2 },
        ) {
            ErrorSnackBar(lce)
        }
    }
}

@Composable
private fun ErrorSnackBar(lce: LCE) {
    Box(Modifier.fillMaxSize()) {
        Snackbar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(largeDp)
        ) {
            Text(text = (lce as? LCE.Error)?.message.orEmpty())
        }
    }
}

@Composable
private fun LoadingBox() {
    val interactionSource = remember {
        MutableInteractionSource()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(loadingBackgroundColor)
            .clickable(interactionSource, null, onClick = {})
    )
    CircularProgressIndicator()
}
