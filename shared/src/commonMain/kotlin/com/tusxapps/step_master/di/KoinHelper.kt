package com.tusxapps.step_master.di

import com.tusxapps.step_master.viewModels.auth.EmailConfirmationViewModel
import com.tusxapps.step_master.data.network.API
import com.tusxapps.step_master.data.network.networkClient
import com.tusxapps.step_master.data.repositories.AuthRepositoryImpl
import com.tusxapps.step_master.domain.AuthRepository
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
    viewModels()
}

private fun Module.repositories() {
    factory<AuthRepository> { AuthRepositoryImpl(get()) }
}

private fun Module.network() {
    single(createdAtStart = true) { networkClient }
    single(createdAtStart = true) { API(get()) }
}

private fun Module.viewModels() {
    factory { LoginViewModel(get()) }
    factory { PasswordRecoveryViewModel() }
    factory { RegisterViewModel() }
    factory { EmailConfirmationViewModel() }
}

expect fun platformModule(): Module
