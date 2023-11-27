package com.tusxapps.step_master.di

import com.russhwolf.settings.Settings
import com.tusxapps.step_master.data.network.API
import com.tusxapps.step_master.data.network.getHttpClient
import com.tusxapps.step_master.data.prefs.PreferencesStorage
import com.tusxapps.step_master.data.repositories.AuthRepositoryImpl
import com.tusxapps.step_master.data.repositories.RegionRepositoryImpl
import com.tusxapps.step_master.domain.auth.AuthRepository
import com.tusxapps.step_master.domain.region.RegionRepository
import com.tusxapps.step_master.viewModels.auth.EmailConfirmationViewModel
import com.tusxapps.step_master.viewModels.auth.LoginViewModel
import com.tusxapps.step_master.viewModels.auth.PasswordRecoveryViewModel
import com.tusxapps.step_master.viewModels.auth.RegisterViewModel
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
    single(createdAtStart = true) { getHttpClient() }
    single(createdAtStart = true) { API(get()) }
}

private fun Module.storage() {
    single(createdAtStart = true) { PreferencesStorage(Settings()) }
}

private fun Module.viewModels() {
    factory { LoginViewModel(get()) }
    factory { PasswordRecoveryViewModel(get()) }
    factory { RegisterViewModel(get(), get()) }
    factory { EmailConfirmationViewModel(get()) }
}

expect fun platformModule(): Module
