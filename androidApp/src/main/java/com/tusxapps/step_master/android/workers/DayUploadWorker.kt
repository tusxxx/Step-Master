package com.tusxapps.step_master.android.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkerParameters
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataSource
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.Field
import com.google.android.gms.fitness.request.DataReadRequest
import com.google.android.gms.fitness.result.DataReadResponse
import com.tusxapps.step_master.data.network.API
import com.tusxapps.step_master.data.prefs.PreferencesStorage
import com.tusxapps.step_master.domain.exceptions.DayExistsException
import kotlinx.coroutines.runBlocking
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

class DayUploadWorker(
    private val appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams), KoinComponent {
    private val api by inject<API>()
    private val preferencesStorage by inject<PreferencesStorage>()
    override suspend fun doWork(): Result {
        Log.d(TAG, "Worker started")
        tryGetGoogleFitSteps()
        return Result.success()
    }

    private suspend fun tryGetGoogleFitSteps() {
        val todayStr = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        val startTime = LocalDate.now().atStartOfDay(ZoneId.systemDefault())
        val endTime = LocalDateTime.now().atZone(ZoneId.systemDefault())

        val datasource = getDatasource()
        val request = getRequest(datasource, startTime, endTime)

        Fitness.getHistoryClient(
            appContext,
            GoogleSignIn.getAccountForExtension(appContext, FITNESS_OPTIONS)
        )
            .readData(request)
            .addOnSuccessListener { dataReadResponse ->
                val totalSteps = dataReadResponse.toTotalTodaySteps()

                runBlocking {
                    Log.d(TAG, "Total steps: $totalSteps")
                    try {
                        sendDay(totalSteps, todayStr)
                    } catch (e: DayExistsException) {
                        editDay(totalSteps, todayStr)
                    } catch (e: Exception) {
                        Log.e(TAG, "Worker failed")
                        Log.e(TAG, e.toString())
                    }
                }
            }
    }

    private fun DataReadResponse.toTotalTodaySteps() = this.buckets
        .flatMap { it.dataSets }
        .flatMap { it.dataPoints }
        .sumOf { it.getValue(Field.FIELD_STEPS).asInt() }

    private suspend fun editDay(totalSteps: Int, todayStr: String) {
        // todo get current day
        val id = api.getDays().result.last().id
        api.editDay(
            id,
            1f,
            totalSteps,
            15f,
            30f,
            150000,
            100f,
            todayStr
        )
    }

    private suspend fun sendDay(totalSteps: Int, todayStr: String) {
        api.uploadDay(
            1f,
            totalSteps,
            15f,
            30f,
            150000,
            100f,
            todayStr
        )
    }

    private fun getRequest(
        datasource: DataSource,
        startTime: ZonedDateTime,
        endTime: ZonedDateTime
    ) = DataReadRequest.Builder()
        .aggregate(datasource)
        .bucketByTime(1, TimeUnit.DAYS)
        .setTimeRange(startTime.toEpochSecond(), endTime.toEpochSecond(), TimeUnit.SECONDS)
        .build()

    private fun getDatasource() = DataSource.Builder()
        .setAppPackageName("com.google.android.gms")
        .setDataType(DataType.TYPE_STEP_COUNT_DELTA)
        .setType(DataSource.TYPE_DERIVED)
        .setStreamName("estimated_steps")
        .build()

    companion object {
        const val TAG = "WM_TAG"
        val FITNESS_OPTIONS = FitnessOptions.builder()
            .addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
            .build()
        val WORK_REQUEST = PeriodicWorkRequestBuilder<DayUploadWorker>(
            15,
            TimeUnit.MINUTES,
            5,
            TimeUnit.MINUTES
        ).addTag(TAG).build()
    }
}