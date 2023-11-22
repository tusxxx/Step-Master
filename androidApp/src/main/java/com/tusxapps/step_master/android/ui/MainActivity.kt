package com.tusxapps.step_master.android.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import cafe.adriel.voyager.navigator.Navigator
import com.tusxapps.step_master.android.ui.auth.login.LoginScreen
import com.tusxapps.step_master.android.ui.main.summary.SummaryScreen
import com.tusxapps.step_master.android.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApplicationTheme {
                Surface {
                    Navigator(LoginScreen)
                }
            }
        }
    }
}

