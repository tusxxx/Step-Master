package com.tusxapps.step_master.android.utils

import android.content.Context
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataSource
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.data.Field
import com.google.android.gms.fitness.request.DataReadRequest
import com.google.android.gms.fitness.result.DataReadResponse
import com.tusxapps.step_master.data.prefs.PreferencesStorage
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.concurrent.TimeUnit

class FitnessService(
    private val appContext: Context,
    private val preferencesStorage: PreferencesStorage
) {
    fun tryGetGoogleFitSteps(onDataReceived: () -> Unit = {}) {
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
                    preferencesStorage.todaySteps = totalSteps
                    onDataReceived()
                }
            }

        Fitness.getHistoryClient(
            appContext,
            GoogleSignIn.getAccountForExtension(appContext, FITNESS_OPTIONS)
        )
            .readDailyTotal(DataType.TYPE_DISTANCE_DELTA)
            .addOnSuccessListener {
                runBlocking {
                    val distance = it.dataPoints.sumOf { dataPoint ->
                        dataPoint.getValue(Field.FIELD_DISTANCE).asFloat().toInt()
                    }
                    Log.d(TAG, "Distance: $distance")
                    preferencesStorage.todayDistance = distance
                    onDataReceived()
                }
            }

        Fitness.getHistoryClient(
            appContext,
            GoogleSignIn.getAccountForExtension(appContext, FITNESS_OPTIONS)
        )
            .readDailyTotal(DataType.TYPE_MOVE_MINUTES)
            .addOnSuccessListener {
                runBlocking {
                    val moveMinutes = it.dataPoints.sumOf { dataPoint ->
                        dataPoint.getValue(Field.FIELD_DURATION).asInt()
                    }
                    Log.d(TAG, "Dur: $moveMinutes")
                    preferencesStorage.todayActiveTime = moveMinutes
                    onDataReceived()
                }
            }
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

    private fun DataReadResponse.toTotalTodaySteps() = this.buckets
        .flatMap { it.dataSets }
        .flatMap { it.dataPoints }
        .sumOf { it.getValue(Field.FIELD_STEPS).asInt() }

    companion object {
        val FITNESS_OPTIONS = FitnessOptions.builder()
            .addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
            .addDataType(DataType.AGGREGATE_DISTANCE_DELTA, FitnessOptions.ACCESS_READ)
            .build()

        private const val TAG = "FitnessService"
    }
}