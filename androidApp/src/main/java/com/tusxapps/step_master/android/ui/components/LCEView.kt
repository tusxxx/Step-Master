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
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.tusxapps.step_master.android.R
import com.tusxapps.step_master.android.ui.theme.largeDp
import com.tusxapps.step_master.android.ui.theme.loadingBackgroundColor
import com.tusxapps.step_master.domain.exceptions.DifferentPasswordException
import com.tusxapps.step_master.domain.exceptions.EmptyFieldsException
import com.tusxapps.step_master.domain.exceptions.InvalidConfirmationCode
import com.tusxapps.step_master.domain.exceptions.RegionNotFoundException
import com.tusxapps.step_master.utils.LCE

@Composable
fun LCEView(
    lce: LCE,
    isRefreshable: Boolean = false,
    onRefresh: () -> Unit = {},
    content: @Composable () -> Unit
) {
    if (isRefreshable) {
        val refreshState = rememberSwipeRefreshState(isRefreshing = lce is LCE.Loading)
        SwipeRefresh(state = refreshState, onRefresh = onRefresh) {
            InnerLoader(content, lce)
        }
    } else {
        InnerLoader(content, lce)
    }
}

@Composable
private fun InnerLoader(
    content: @Composable () -> Unit,
    lce: LCE
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        content()
        if (lce == LCE.Loading) {
            LoadingBox()
        }
        AnimatedVisibility(
            visible = lce is LCE.Error,
            enter = slideInVertically { fullHeight -> fullHeight / 2 },
            exit = slideOutVertically { fullHeight -> fullHeight / 2 },
        ) {
            ErrorSnackBar(lce)
        }
    }
}

@Composable
private fun ErrorSnackBar(lce: LCE) {
    val context = LocalContext.current
    val message = remember(lce) {
        (lce as? LCE.Error)?.throwable?.let {
            when (it) {
                is EmptyFieldsException -> context.getString(R.string.exception_empty_fields)
                is InvalidConfirmationCode -> context.getString(R.string.exception_invalid_confirmation_code)
                is DifferentPasswordException -> context.getString(R.string.exception_different_password)
                is RegionNotFoundException -> context.getString(R.string.exception_region_not_found)
                else -> it.message
            }
        } ?: context.getString(R.string.unknown_error)
    }
    Box(Modifier.fillMaxSize()) {
        Snackbar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(largeDp)
        ) {
            Text(text = message)
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
