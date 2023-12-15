package com.tusxapps.step_master.android.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Surface
import androidx.lifecycle.lifecycleScope
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.WorkManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.tusxapps.step_master.android.ui.navigation.MainNavigation
import com.tusxapps.step_master.android.ui.theme.MyApplicationTheme
import com.tusxapps.step_master.android.utils.FitnessService
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
                        requestFitPermissions(acc)
                        setupWorker()
                    }
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        googleSingIn()
        setupWorker()
        setContent {
            MyApplicationTheme {
                Surface {
                    MainNavigation()
                }
            }
        }
    }

    private fun runWorkerImmidiatly() {
        WorkManager.getInstance(applicationContext).enqueue(DayUploadWorker.OT_WORK_REQUEST)
    }

    private fun requestFitPermissions(acc: GoogleSignInAccount?) {
        if (!GoogleSignIn.hasPermissions(acc, FitnessService.FITNESS_OPTIONS)) {
            GoogleSignIn.requestPermissions(
                this,
                1,
                acc,
                FitnessService.FITNESS_OPTIONS
            )
        }
    }

    private fun setupWorker() {
        lifecycleScope.launch {
            val wm = WorkManager.getInstance(applicationContext)
//            val workInfos = wm.getWorkInfosByTag(DayUploadWorker.TAG).await()
//            if (workInfos.isEmpty()) {
//                wm.enqueue(DayUploadWorker.WORK_REQUEST)
//            } else {
//                wm.updateWork(DayUploadWorker.WORK_REQUEST)
//            }
            wm.enqueueUniquePeriodicWork(
                DayUploadWorker.TAG,
                ExistingPeriodicWorkPolicy.UPDATE,
                DayUploadWorker.WORK_REQUEST
            )
        }
    }

    private fun googleSingIn() {
        val account = GoogleSignIn.getLastSignedInAccount(applicationContext)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .addExtension(DayUploadWorker.FITNESS_OPTIONS)
            .build()
        val googleSignInClient = GoogleSignIn.getClient(applicationContext, gso)


        if (account == null) {
            googleSignInClient.revokeAccess().addOnCompleteListener {
                val signInIntent = googleSignInClient.signInIntent
                launcher.launch(signInIntent)
            }
        } else {
            requestFitPermissions(account)
        }
    }
}
