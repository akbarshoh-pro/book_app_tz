package com.example.book_app_tz.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.book_app_tz.data.remote.api.Api
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class InternetModule {
    @Provides
    @Singleton
    fun provideGson() : Gson = Gson()

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context) : OkHttpClient = OkHttpClient
        .Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(ChuckerInterceptor(context))
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit = Retrofit
        .Builder()
        .baseUrl("https://skoob.ru/api/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit) : Api = retrofit.create(Api::class.java)
}