package com.example.appforportfolio.ui.settings

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.example.appforportfolio.ServiceLocator
import com.example.appforportfolio.databinding.FragmentSettingsBinding
import com.example.appforportfolio.manager.NightMode
import com.example.appforportfolio.manager.NightMode.*
import com.example.appforportfolio.manager.SharedPrefsManager
import com.example.appforportfolio.ui.BaseFragment
import javax.inject.Inject

class SettingsFragment : BaseFragment<FragmentSettingsBinding>(
    FragmentSettingsBinding::inflate
) {

    @Inject
    lateinit var sharedPreferences: SharedPrefsManager

    override fun onAttach(context: Context) {
        val appComponent = ServiceLocator(requireContext()).appComponent
        appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSwitchCorrectValue()
        setSwitchNightThemeListener()
    }

    private fun setSwitchCorrectValue() {
        when(sharedPreferences.nightMode) {
            LIGHT -> binding.switchNightTheme.isChecked = false
            DARK -> binding.switchNightTheme.isChecked = true
            SYSTEM -> { }
        }
    }

    private fun setSwitchNightThemeListener() {
        binding.switchNightTheme.setOnCheckedChangeListener { compoundButton, nightMode ->
            if(nightMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                sharedPreferences.nightMode = DARK
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                sharedPreferences.nightMode = LIGHT
            }
        }
    }
}