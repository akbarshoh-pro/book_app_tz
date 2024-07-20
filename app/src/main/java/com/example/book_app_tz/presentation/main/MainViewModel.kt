package com.example.book_app_tz.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.book_app_tz.domain.AppRepository
import com.example.book_app_tz.utils.ScreenStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: AppRepository,
) : ViewModel(), MainContract.ViewModel {
    override val container = container<MainContract.UIState, MainContract.SideEffect>(
        MainContract.UIState()
    )

    override fun onEventDispatcher(intent: MainContract.Intent) = intent {
        when(intent) {
            MainContract.Intent.LoadAllBooks -> {
                reduce { state.copy(screenStatus = ScreenStatus.LOADING) }
                repo.getAllBooks().onEach { result ->
                    result.onSuccess { list ->
                        reduce { state.copy(screenStatus = ScreenStatus.SUCCESS, books = list) }
                    }
                    result.onFailure { error ->
                        reduce { state.copy(screenStatus = ScreenStatus.FAILURE, message = error.message.toString()) }
                    }
                }
                    .launchIn(viewModelScope)
            }
            is MainContract.Intent.OpenDetail -> {
                postSideEffect(MainContract.SideEffect.OpenDetail(intent.data))
            }
        }
    }
}