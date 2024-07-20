package com.example.book_app_tz.presentation.detail

import com.example.book_app_tz.data.model.BookData
import com.example.book_app_tz.utils.ScreenStatus
import org.orbitmvi.orbit.ContainerHost

interface DetailsContract {
    interface ViewModel : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }

    class UIState

    sealed interface SideEffect

    sealed interface Intent {
        data object BackMain : Intent
    }
}