package com.gyurme.amphibians.network

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Amphibian(
    val name: String,
    val type: String,
    val description: String,
    @SerializedName(value = "img_src")
    val imgSrc: String
)
