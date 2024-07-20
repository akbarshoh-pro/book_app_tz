package com.example.book_app_tz.presentation.detail

import com.example.book_app_tz.data.model.BookData
import com.example.book_app_tz.navigation.AppNavigator
import javax.inject.Inject


interface DetailsDirections {
    suspend fun backMain()
}

class DetailsDirectionsImp @Inject constructor(
    private val navigator: AppNavigator
) : DetailsDirections {

    override suspend fun backMain() {
        navigator.back()
    }
}