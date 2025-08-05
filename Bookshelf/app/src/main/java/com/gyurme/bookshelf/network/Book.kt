package com.gyurme.bookshelf.network

import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val id: String,
    val volumeInfo: BookInfo
)

@Serializable
data class BookInfo(
    val imageLinks: ImageInfo
)

@Serializable
data class ImageInfo(
    val smallThumbnail: String,
    val thumbnail: String
)

@Serializable
data class BookResults(
    val items: List<Book>
)
