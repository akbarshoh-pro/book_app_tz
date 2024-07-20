package com.example.book_app_tz.data.remote.response

import com.example.book_app_tz.data.model.BookData
import com.google.gson.annotations.SerializedName

data class BookResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: BooksList
)

data class BooksList(
    @SerializedName("books")
    val books: List<BookData>
)

/*
* {
                "bookId": "405233",
                "categoryId": "144",
                "authorId": "3614",
                "section": "Крутой детектив",
                "title": "Истории для любопытных. Из коллекции Альфреда Хичкока",
                "alias": "704K, 153 с.",
                "fileName": "339755",
                "summary": "<p>Книга продолжает серию, открывшуюся сборником &laquo;Истории, рассказанные шепотом&raquo;. Включенные в нее новеллы, принадлежащие к разным жанрам, объединяет одно: в основе фабулы каждой из ни? лежит загадка, которую необходимо разгадать. Попробуйте сделать это, не дожидаясь разъяснений автора.</p>",
                "content": "",
                "popularity": "42579",
                "likes": "0",
                "dislikes": "0",
                "downloads": "0",
                "counter": "0",
                "author": "Джек Финней",
                "comments": 4,
                "rating": "3.8",
                "image": "https://skoob.ru/uploads/books/54afe597376c.jpeg"
            },
* */