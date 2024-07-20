package com.example.book_app_tz

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.example.book_app_tz.data.model.BookData
import com.example.book_app_tz.domain.AppRepository
import com.example.book_app_tz.domain.imp.AppRepositoryImp
import com.example.book_app_tz.navigation.AppNavigationHandler
import com.example.book_app_tz.navigation.AppNavigator
import com.example.book_app_tz.presentation.main.MainScreen
import com.example.book_app_tz.ui.theme.Book_app_tzTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var handler: AppNavigationHandler

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Book_app_tzTheme {
                Surface {
                    Navigator(screen = MainScreen()) { navigator ->
                        handler.uiNavigator
                            .onEach { it.invoke(navigator) }
                            .launchIn(lifecycleScope)
                        CurrentScreen()
                    }
                }
            }
        }
    }
}

