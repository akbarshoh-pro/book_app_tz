package com.example.book_app_tz.presentation.main

import com.example.book_app_tz.data.model.BookData
import com.example.book_app_tz.navigation.AppNavigator
import com.example.book_app_tz.presentation.detail.DetailsScreen
import javax.inject.Inject

interface MainDirections {
    suspend fun openDetails(book: BookData)
}

class MainDirectionsImp @Inject constructor(
    private val navigator: AppNavigator
) : MainDirections {
    override suspend fun openDetails(book: BookData) {
        navigator.addScreen(DetailsScreen(book))
    }
}