package com.example.appforportfolio

import android.app.Application
import android.content.Context
import com.example.appforportfolio.di.AppComponent
import com.example.appforportfolio.di.DaggerAppComponent

class MyApp : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }

    val Context.appComponent: AppComponent
        get() = when (this) {
            is MyApp -> appComponent
            else -> applicationContext.appComponent
        }
}