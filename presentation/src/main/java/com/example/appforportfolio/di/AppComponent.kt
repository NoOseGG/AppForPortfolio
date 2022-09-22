package com.example.appforportfolio.di

import com.example.data.di.DataModule
import com.example.data.di.NetworkModule
import com.example.data.di.RepositoryModule
import dagger.Component

@Component(modules = [DataModule::class])
interface AppComponent {



}