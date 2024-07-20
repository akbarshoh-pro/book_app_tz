package com.example.book_app_tz.presentation.detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val directions: DetailsDirections
) : ViewModel(), DetailsContract.ViewModel {
    override val container = container<DetailsContract.UIState, DetailsContract.SideEffect>(DetailsContract.UIState())

    override fun onEventDispatcher(intent: DetailsContract.Intent) = intent {
        when(intent) {
            DetailsContract.Intent.BackMain -> directions.backMain()
        }
    }
}