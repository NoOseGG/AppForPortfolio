package com.example.appforportfolio.manager

import android.content.Context

class SharedPrefsManager(
    context: Context
) {

    val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    var nightMode: NightMode by enumPrefs(KEY_NIGHT_MODE, NightMode.SYSTEM)

    private inline fun <reified E : Enum<E>> enumPrefs(key: String, defaultValue: E) =
        PrefsDelegate(
            sharedPreferences,
            getValue = { getString(key, null)?.let(::enumValueOf) ?: defaultValue },
            setValue = { putString(key, it.name) }
        )


    companion object {
        private const val PREFS_NAME = "PREFS"
        private const val KEY_NIGHT_MODE = "NIGHT_MODE"
    }
}