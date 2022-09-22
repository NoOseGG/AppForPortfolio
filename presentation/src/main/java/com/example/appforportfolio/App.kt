package com.example.appforportfolio

import android.app.Application
import android.content.Context
import com.example.appforportfolio.di.AppComponent
import com.example.appforportfolio.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        appComponent =  DaggerAppComponent.create()
        super.onCreate()
    }

    val Context.appComponent: AppComponent
    get() = when(this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }
}