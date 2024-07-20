package com.example.book_app_tz.domain

import com.example.book_app_tz.data.model.BookData
import kotlinx.coroutines.flow.Flow

interface
AppRepository {
    fun getAllBooks() : Flow<Result<List<BookData>>>
    fun getBook(index: Int) : BookData
}