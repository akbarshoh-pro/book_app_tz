package com.example.book_app_tz.di

import com.example.book_app_tz.navigation.AppNavigationDispatcher
import com.example.book_app_tz.navigation.AppNavigationHandler
import com.example.book_app_tz.navigation.AppNavigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @[Binds Singleton]
    fun bindHandler(impl: AppNavigationDispatcher): AppNavigationHandler

    @[Binds Singleton]
    fun bindNavigator(impl: AppNavigationDispatcher): AppNavigator
}