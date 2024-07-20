package com.example.book_app_tz.di

import com.example.book_app_tz.presentation.detail.DetailsDirections
import com.example.book_app_tz.presentation.detail.DetailsDirectionsImp
import com.example.book_app_tz.presentation.main.MainDirections
import com.example.book_app_tz.presentation.main.MainDirectionsImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DirectionsModule {
    @Binds
    fun bindMain(imp: MainDirectionsImp) : MainDirections

    @Binds
    fun bindDetails(imp: DetailsDirectionsImp) : DetailsDirections
}