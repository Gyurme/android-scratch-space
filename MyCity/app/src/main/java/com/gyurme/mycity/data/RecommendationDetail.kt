package com.gyurme.mycity.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class RecommendationDetail(
    val id: Long,
    val recommendation: Recommendation,
    @DrawableRes val image: Int,
    @StringRes val description: Int)
