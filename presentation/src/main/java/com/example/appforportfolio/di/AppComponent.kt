package com.example.appforportfolio.di

import com.example.appforportfolio.ui.details.DetailsFragment
import com.example.appforportfolio.ui.feed.FeedFragment
import com.example.data.di.DataModule
import com.example.data.di.NetworkModule
import com.example.data.di.RepositoryModule
import dagger.Component

@Component(modules = [DataModule::class])
interface AppComponent {

    fun inject(feedFragment: FeedFragment)

    fun inject(detailsFragment: DetailsFragment)
}