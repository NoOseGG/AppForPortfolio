package com.example.appforportfolio.di

import android.content.Context
import com.example.appforportfolio.manager.SharedPrefsManager
import dagger.Module
import dagger.Provides

@Module
class AppModule(
    private val context: Context
) {

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideSharedPreferences(
        context: Context
    ): SharedPrefsManager {
        return SharedPrefsManager(context)
    }
}