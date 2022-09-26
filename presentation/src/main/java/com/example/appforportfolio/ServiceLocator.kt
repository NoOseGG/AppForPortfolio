package com.example.appforportfolio

import com.example.appforportfolio.di.AppComponent
import com.example.appforportfolio.di.DaggerAppComponent

object ServiceLocator {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.create()
    }
}