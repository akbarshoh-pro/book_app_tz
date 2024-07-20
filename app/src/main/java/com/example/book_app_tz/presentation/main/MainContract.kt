package com.example.book_app_tz.presentation.main

import androidx.core.app.NotificationCompat.MessagingStyle.Message
import com.example.book_app_tz.data.model.BookData
import com.example.book_app_tz.utils.ScreenStatus
import org.orbitmvi.orbit.ContainerHost

interface MainContract {
    interface ViewModel : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    data class UIState(
        val screenStatus: ScreenStatus = ScreenStatus.INITIAL,
        val books: List<BookData> = listOf(),
        val message: String = ""
    )

    sealed interface SideEffect

    sealed interface Intent {
        data object LoadAllBooks : Intent
        data class OpenDetail(
            val data: BookData
        ) : Intent
    }
}