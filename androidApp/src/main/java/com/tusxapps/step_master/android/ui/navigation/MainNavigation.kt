package com.tusxapps.step_master.android.ui.navigation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.tusxapps.step_master.android.ui.auth.login.LoginScreen
import com.tusxapps.step_master.android.ui.main.profile.ProfileScreen
import com.tusxapps.step_master.android.ui.main.summary.SummaryScreen
import com.tusxapps.step_master.domain.auth.AuthRepository
import org.koin.androidx.compose.get

@Composable
fun MainNavigation() {
    val authRepo = get<AuthRepository>()
    val isAuthorized = remember {
        authRepo.isAuthorized()
    }
    val bottomScreens: List<BottomBarScreen> = remember {
        listOf(SummaryScreen, ProfileScreen)
    }

    Navigator(if (isAuthorized) SummaryScreen else LoginScreen) {
        val navigator = LocalNavigator.currentOrThrow
        val currentScreen = navigator.lastItem

        Scaffold(
            content = {
                Box(modifier = Modifier.padding(it)) {
                    CurrentScreen()
                }
            },
            bottomBar = {
                if (bottomScreens.any { currentScreen.key == it.key }) {
                    NavigationBar(
                        modifier = Modifier
                            .shadow(
                                elevation = 4.dp,
                                shape = RoundedCornerShape(topEnd = 12.dp, topStart = 12.dp)
                            )
                            .clip(RoundedCornerShape(topEnd = 12.dp, topStart = 12.dp))
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.secondary,
                                shape = RoundedCornerShape(topEnd = 12.dp, topStart = 12.dp)
                            )
                            .height(80.dp),
                        containerColor = MaterialTheme.colorScheme.secondary
                    ) {
                        bottomScreens.forEach {
                            NavigationItem(it)
                        }
                    }
                }
            }
        )
    }
}

@Composable
private fun RowScope.NavigationItem(screen: BottomBarScreen) {
    val navigator = LocalNavigator.currentOrThrow

    NavigationBarItem(
        selected = navigator.lastItem.key == screen.key,
        label = { Text(text = screen.options.name) },
        onClick = {
            if (screen in navigator.items) {
                navigator.popUntil { it.key == screen.key }
            } else {
                navigator.push(screen)
            }
        },
        icon = { Icon(painter = screen.options.icon, contentDescription = "null") },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.primary,
            indicatorColor = MaterialTheme.colorScheme.secondary,
        )
    )
}