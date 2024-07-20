package com.example.book_app_tz.navigation

sealed class Screen(val route: String) {
    data object MainScreen : Screen("main_screen")
    data object DetailsScreen : Screen("details_screen")
}