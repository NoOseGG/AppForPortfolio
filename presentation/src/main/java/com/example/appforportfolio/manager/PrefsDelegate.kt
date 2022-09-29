package com.example.appforportfolio.manager

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlin.reflect.KProperty

class PrefsDelegate<T>(
    private val sharedPreferences: SharedPreferences,
    private val getValue: SharedPreferences.() -> T,
    private val setValue: SharedPreferences.Editor.(T) -> Unit
) {

    operator fun getValue(thisRef: Any, property: KProperty<*>): T {
        return sharedPreferences.getValue()
    }

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        sharedPreferences.edit { setValue(value) }
    }
}