package com.example.book_app_tz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.book_app_tz.navigation.Navigation
import com.example.book_app_tz.ui.theme.Book_app_tzTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Book_app_tzTheme {
                Navigation()
            }
        }
    }
}

