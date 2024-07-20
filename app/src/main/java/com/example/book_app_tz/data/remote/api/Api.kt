package com.example.book_app_tz.data.remote.api

import com.example.book_app_tz.data.remote.request.BookRequest
import com.example.book_app_tz.data.remote.response.BookResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface Api {
    @POST("v1/app/books/new/")
    suspend fun getAllBooks(@Header("Authorization") token: String) : Response<BookResponse>
}