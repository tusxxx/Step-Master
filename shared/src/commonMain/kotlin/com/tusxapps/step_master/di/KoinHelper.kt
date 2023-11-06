package com.tusxapps.step_master.di

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
    factory { LoginViewModel() }
    factory { PasswordRecoveryViewModel() }
    factory { RegisterViewModel() }
    factory { EmailConfirmationViewModel() }
}

expect fun platformModule(): Module
