package com.example.book_app_tz.di

import com.example.book_app_tz.domain.AppRepository
import com.example.book_app_tz.domain.imp.AppRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindRepository(imp: AppRepositoryImp) : AppRepository
}