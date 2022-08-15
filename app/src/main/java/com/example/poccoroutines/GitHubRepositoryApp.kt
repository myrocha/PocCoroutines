package com.example.poccoroutines

import android.app.Application

open class GitHubRepositoryApp : Application() {

    override fun onCreate() {
        super.onCreate()
        KoinInitializer.initialize(this)
    }
}