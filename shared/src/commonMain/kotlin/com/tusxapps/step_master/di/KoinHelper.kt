package com.tusxapps.step_master.di

import com.russhwolf.settings.Settings
import com.tusxapps.step_master.data.network.API
import com.tusxapps.step_master.data.network.networkClient
import com.tusxapps.step_master.data.prefs.PreferencesStorage
import com.tusxapps.step_master.data.prefs.PreferencesStorageImpl
import com.tusxapps.step_master.data.repositories.AuthRepositoryImpl
import com.tusxapps.step_master.data.repositories.RegionRepositoryImpl
import com.tusxapps.step_master.domain.auth.AuthRepository
import com.tusxapps.step_master.domain.region.RegionRepository
import com.tusxapps.step_master.viewModels.auth.EmailConfirmationViewModel
import com.tusxapps.step_master.viewModels.auth.LoginViewModel
import com.tusxapps.step_master.viewModels.auth.PasswordRecoveryViewModel
import com.tusxapps.step_master.viewModels.auth.RegisterViewModel
import com.tusxapps.step_master.viewModels.main.ActivityViewModel
import com.tusxapps.step_master.viewModels.main.SummaryViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

fun initKoin() {
    val koinApp = startKoin {
        modules(appModule())
    }
}

fun appModule() = listOf(
    commonModule(), platformModule()
)

fun commonModule() = module {
    repositories()
    network()
    storage()
    viewModels()
}

private fun Module.repositories() {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<RegionRepository> { RegionRepositoryImpl(get()) }
}

private fun Module.network() {
    single(createdAtStart = true) { networkClient }
    single(createdAtStart = true) { API(get()) }
}

private fun Module.storage() {
    single(createdAtStart = true) { Settings() }
    single<PreferencesStorage> { PreferencesStorageImpl(get()) }
}

private fun Module.viewModels() {
    factory { LoginViewModel(get()) }
    factory { PasswordRecoveryViewModel(get()) }
    factory { RegisterViewModel(get(), get()) }
    factory { EmailConfirmationViewModel(get()) }
    factory { SummaryViewModel() }
    factory { ActivityViewModel() }
}

expect fun platformModule(): Module
