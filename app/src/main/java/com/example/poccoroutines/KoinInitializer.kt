package com.example.poccoroutines

import android.app.Application
import com.example.poccoroutines.di.gitHubModule
import com.example.poccoroutines.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

object KoinInitializer {
    val modules = listOf(
        networkModule,
        gitHubModule
    )

    fun initialize(application: Application) {
        startKoin {
            androidContext(application)
            modules(modules)
        }
    }
}