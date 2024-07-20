package com.example.book_app_tz.data.model

import com.google.gson.annotations.SerializedName

data class BookData(
    @SerializedName("bookId")
    val bookId: String,
    @SerializedName("categoryId")
    val categoryId: String,
    @SerializedName("authorId")
    val authorId: String,
    @SerializedName("section")
    val section: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("alias")
    val alias: String,
    @SerializedName("fileName")
    val fileName: String,
    @SerializedName("summary")
    val summary: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("popularity")
    val popularity: String,
    @SerializedName("likes")
    val likes: String,
    @SerializedName("dislikes")
    val dislikes: String,
    @SerializedName("downloads")
    val downloads: String,
    @SerializedName("counter")
    val counter: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("comments")
    val comments: Int,
    @SerializedName("rating")
    val rating: String,
    @SerializedName("image")
    val image: String
)
