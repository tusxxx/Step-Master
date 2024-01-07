package com.tusxapps.step_master.android.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkerParameters
import com.tusxapps.step_master.android.utils.FitnessService
import com.tusxapps.step_master.data.network.API
import com.tusxapps.step_master.data.prefs.PreferencesStorage
import com.tusxapps.step_master.domain.exceptions.DayExistsException
import kotlinx.coroutines.runBlocking
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

class DayUploadWorker(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams), KoinComponent {
    private val api by inject<API>()
    private val fitnessService by inject<FitnessService>()
    private val preferencesStorage by inject<PreferencesStorage>()

    override suspend fun doWork(): Result {
        Log.d(TAG, "Worker ${this.tags} started")
        try {
            tryGetGoogleFitSteps()
        } catch (e: Exception) {
            Log.d(TAG, "Exception in worker: ${e.message}")
            Log.d(TAG, "$e")
        }
        return Result.success()
    }

    private suspend fun tryGetGoogleFitSteps() {
        val todayStr = LocalDateTime.now().toString()

        fitnessService.tryGetGoogleFitSteps {
            runBlocking {
                uploadDayToServer(todayStr)
            }
        }
    }

    private suspend fun uploadDayToServer(todayStr: String) {
        val todaySteps = preferencesStorage.todaySteps
        val goalSteps = preferencesStorage.goalSteps
        val goalActiveTime = preferencesStorage.goalActiveTime.toFloat()
        val todayActiveTime = preferencesStorage.todayActiveTime.toFloat()
        val goalCalories = preferencesStorage.goalCalories.toFloat()
        val todayCalories = preferencesStorage.todayCalories.toFloat()

        try {
            api.uploadDay(
                calories = todayCalories,
                steps = todaySteps,
                distance = todayActiveTime,
                planSteps = goalSteps,
                planDistance = goalActiveTime,
                planCalories = goalCalories,
                date = todayStr
            )
        } catch (e: DayExistsException) {
            val id = api.getDays().result.last().id
            api.editDay(
                id = id,
                calories = todayCalories,
                steps = todaySteps,
                distance = todayActiveTime,
                planDistance = goalActiveTime,
                planSteps = goalSteps,
                planCalories = goalCalories,
                date = todayStr
            )
        } catch (e: Exception) {
            Log.e(TAG, "Worker failed")
            Log.e(TAG, e.toString())
        }
    }

    companion object {
        const val TAG = "WM_TAG"

        val WORK_REQUEST = PeriodicWorkRequestBuilder<DayUploadWorker>(
            20,
            TimeUnit.MINUTES,
            5,
            TimeUnit.MINUTES
        ).build()

        /**
         * For debug purpose
         */
        val OT_WORK_REQUEST =
            OneTimeWorkRequestBuilder<DayUploadWorker>()
                .addTag(TAG+"_OT")
                .build()
    }
}