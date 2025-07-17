package com.gyurme.mycity.data

import androidx.annotation.StringRes

data class RecommendationDetail(
    val id: Long,
    val recommendation: Recommendation,
    @StringRes val description: Int)
