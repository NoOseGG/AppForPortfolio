package com.example.appforportfolio

import android.content.Context
import com.example.appforportfolio.di.AppComponent
import com.example.appforportfolio.di.AppModule
import com.example.appforportfolio.di.DaggerAppComponent

class ServiceLocator(
    private val context: Context
) {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(context))
            .build()
    }
}