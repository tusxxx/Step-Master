package com.tusxapps.step_master.android.di

import com.tusxapps.step_master.android.workers.DayUploadWorker
import org.koin.androidx.workmanager.dsl.workerOf
import org.koin.dsl.module

val androidModule = module {
    workerOf(::DayUploadWorker)
}