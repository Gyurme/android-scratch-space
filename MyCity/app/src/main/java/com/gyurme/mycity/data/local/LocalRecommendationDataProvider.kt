package com.gyurme.mycity.data.local

import androidx.compose.ui.res.painterResource
import com.gyurme.mycity.R
import com.gyurme.mycity.data.Category
import com.gyurme.mycity.data.Recommendation

object LocalRecommendationDataProvider {
    private val allRecommendations = listOf(
        Recommendation(
            id = 1L,
            Category.Cafe,
            R.string.recommendation_cafe_1
        ),
        Recommendation(
            id = 2L,
            Category.Restaurant,
            R.string.recommendation_restaurant_1
        ),
        Recommendation(
            id = 1L,
            Category.Attraction,
            R.string.recommendation_detail_attraction_sky_tower
        ),
    )
}