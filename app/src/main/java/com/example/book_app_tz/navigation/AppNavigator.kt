package com.example.book_app_tz.navigation

import cafe.adriel.voyager.core.screen.Screen



interface AppNavigator {
    suspend fun addScreen(screen: Screen)
    suspend fun replaceScreen(screen: Screen)
    suspend fun replaceAll(screen: Screen)
    suspend fun back()
    suspend fun backUntil(screen: Screen)
    suspend fun backToRoot()
}