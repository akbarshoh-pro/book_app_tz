package com.example.book_app_tz.data.remote.request

import com.google.gson.annotations.SerializedName

data class BookRequest(
    @SerializedName("cat_id")
    val catId: Int
)