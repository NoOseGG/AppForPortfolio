package com.example.appforportfolio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavHost
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.appforportfolio.databinding.ActivityMainBinding
import com.example.appforportfolio.di.AppComponent
import com.example.appforportfolio.manager.NightMode
import com.example.appforportfolio.manager.SharedPrefsManager
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.container) as NavHost)
            .navController
    }
    private val appComponent = ServiceLocator(this).appComponent

    @Inject
    lateinit var sharedPrefs: SharedPrefsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        appComponent.inject(this)
        AppCompatDelegate.setDefaultNightMode(
            when (sharedPrefs.nightMode) {
                NightMode.LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
                NightMode.DARK -> AppCompatDelegate.MODE_NIGHT_YES
                NightMode.SYSTEM -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            }
        )
        setContentView(binding.root)

        binding.bottomNavMenu.setupWithNavController(navController)
    }
}
