package com.example.appforportfolio.di

import com.example.data.di.NetworkModule
import com.example.data.di.RepositoryModule
import dagger.Component

@Component(modules = [NetworkModule::class, RepositoryModule::class])
interface AppComponent {


}