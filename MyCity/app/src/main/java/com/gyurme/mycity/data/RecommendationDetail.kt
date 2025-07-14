package com.gyurme.mycity.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class RecommendationDetail(
    val id: Long,
    val category: Category,
    @StringRes val description: Int,
    @DrawableRes val image: Int
)
