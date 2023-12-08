package com.tusxapps.step_master.di

import com.tusxapps.step_master.utils.AndroidFileStorage
import com.tusxapps.step_master.utils.FileStorage
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module  = module {
    factory<FileStorage> { AndroidFileStorage(get()) }
}