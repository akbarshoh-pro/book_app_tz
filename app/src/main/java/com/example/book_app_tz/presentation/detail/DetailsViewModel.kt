package com.example.book_app_tz.presentation.detail

import androidx.lifecycle.ViewModel
import com.example.book_app_tz.data.model.BookData
import com.example.book_app_tz.domain.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repo: AppRepository
) : ViewModel(), DetailsContract.ViewModel {
    override val container = container<DetailsContract.UIState, DetailsContract.SideEffect>(DetailsContract.UIState(
        data = BookData(
            bookId = "1",
            categoryId = "FIC",
            authorId = "A1",
            section = "Literature",
            title = "To Kill a Mockingbird",
            alias = "Mockingbird",
            fileName = "to_kill_a_mockingbird.pdf",
            summary = "A novel about the serious issues of rape and racial inequality narrated by a young girl.",
            content = "Full content of the book...",
            popularity = "High",
            likes = "1200",
            dislikes = "50",
            downloads = "3000",
            counter = "500",
            author = "Harper Lee",
            comments = 200,
            rating = "4.9",
            image = "https://example.com/to_kill_a_mockingbird.jpg"
        )
    ))

    override fun onEventDispatcher(intent: DetailsContract.Intent) = intent {
        when(intent) {
            is DetailsContract.Intent.LoadDada -> {
                reduce { state.copy(data = repo.getBook(intent.index)) }
            }
            DetailsContract.Intent.BackMain -> {
                postSideEffect(DetailsContract.SideEffect.BackMain)
            }
        }
    }
}