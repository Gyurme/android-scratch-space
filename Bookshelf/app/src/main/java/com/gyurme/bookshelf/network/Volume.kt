package com.gyurme.bookshelf.network

import kotlinx.serialization.Serializable

@Serializable
data class Volume(
    val id: String,
    val volumeInfo: VolumeInfo
)

@Serializable
data class VolumeInfo(
    val imageLinks: ImageInfo
)

@Serializable
data class ImageInfo(
    val smallThumbnail: String,
    val thumbnail: String
)

@Serializable
data class VolumeResults(
    val items: List<Volume>
)
