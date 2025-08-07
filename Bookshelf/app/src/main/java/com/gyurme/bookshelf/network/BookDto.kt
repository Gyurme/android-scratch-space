package com.gyurme.bookshelf.network

import kotlinx.serialization.Serializable

@Serializable
data class BookDto(
    val id: String,
    val volumeInfo: BookDetails
)

@Serializable
data class BookDetails(
    val imageLinks: ImageInfo
)

@Serializable
data class ImageInfo(
    val smallThumbnail: String,
    val thumbnail: String
)

@Serializable
data class BookResults(
    val items: List<BookDto>
)
