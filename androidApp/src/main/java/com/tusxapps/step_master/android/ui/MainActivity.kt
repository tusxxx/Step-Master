package com.tusxapps.step_master.android.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Surface
import androidx.lifecycle.lifecycleScope
import androidx.work.WorkManager
import androidx.work.await
import cafe.adriel.voyager.navigator.Navigator
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.fitness.Fitness
import com.tusxapps.step_master.android.ui.auth.login.LoginScreen
import com.tusxapps.step_master.android.ui.theme.MyApplicationTheme
import com.tusxapps.step_master.android.workers.DayUploadWorker
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK && result.data != null) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                task.addOnCompleteListener {
                    if (task.isSuccessful) {
                        val acc = task.result
                        Log.d("TAG", "Success login: ")
                        if (!GoogleSignIn.hasPermissions(acc, Fitness.SCOPE_ACTIVITY_READ)) {
                            GoogleSignIn.requestPermissions(
                                this,
                                1,
                                task.result,
                                DayUploadWorker.FITNESS_OPTIONS
                            )
                        }
                        setupWorker()
                    } else {
                        Log.d("TAG", "ffff: ")
                    }
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        googleSingIn()
        setContent {
            MyApplicationTheme {
                Surface {
                    Navigator(LoginScreen)
                }
            }
        }
    }

    private fun setupWorker() {
        lifecycleScope.launch {
            val wm = WorkManager.getInstance(applicationContext)
            val workInfos = wm.getWorkInfosByTag(DayUploadWorker.TAG).await()
            if (workInfos.isEmpty()) {
                wm.enqueue(DayUploadWorker.WORK_REQUEST)
            } else {
                wm.updateWork(DayUploadWorker.WORK_REQUEST)
            }
        }
    }

    private fun googleSingIn() {
        if (GoogleSignIn.getLastSignedInAccount(this) == null) {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .addExtension(DayUploadWorker.FITNESS_OPTIONS)
                .build()
            val googleSignInClient = GoogleSignIn.getClient(this, gso)
            googleSignInClient.revokeAccess().addOnCompleteListener {
                val signInIntent = googleSignInClient.signInIntent
                launcher.launch(signInIntent)
            }
        }
    }
}
