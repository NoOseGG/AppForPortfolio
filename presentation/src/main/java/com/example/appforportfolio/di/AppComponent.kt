package com.example.appforportfolio.di

import com.example.appforportfolio.MainActivity
import com.example.appforportfolio.ui.details.DetailsFragment
import com.example.appforportfolio.ui.feed.FeedFragment
import com.example.appforportfolio.ui.settings.SettingsFragment
import com.example.data.di.DataModule
import com.example.data.di.NetworkModule
import com.example.data.di.RepositoryModule
import dagger.Component

@Component(modules = [AppModule::class, DataModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(feedFragment: FeedFragment)

    fun inject(detailsFragment: DetailsFragment)

    fun inject(settingsFragment: SettingsFragment)
}