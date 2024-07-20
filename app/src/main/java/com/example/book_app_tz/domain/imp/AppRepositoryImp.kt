package com.example.book_app_tz.domain.imp

import com.example.book_app_tz.data.model.BookData
import com.example.book_app_tz.data.remote.api.Api
import com.example.book_app_tz.domain.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepositoryImp @Inject constructor(
    private val api: Api
) : AppRepository {
    private var list: List<BookData> = listOf()

    override fun getAllBooks(): Flow<Result<List<BookData>>> = flow {
        if (list.isEmpty()) {
            val response = api.getAllBooks(token = "Bearer bGlmZXN0eWxlOjY0YzBkMGZlYzcwOGU1MDYyMWViZDMxNA==")

            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    list = body.data.books
                    emit(Result.success(body.data.books))
                } else {
                    emit(Result.failure(Exception("Response body is null")))
                }
            } else {
                emit(Result.failure(Exception("Request failed: ${response.code()} ${response.message()}")))
            }
        } else {
            emit(Result.success(list))
        }
    }

    override fun getBook(index: Int): BookData =
        list[index];

}