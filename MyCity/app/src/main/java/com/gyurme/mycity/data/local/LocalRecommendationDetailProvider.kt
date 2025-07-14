package com.gyurme.mycity.data.local

import com.gyurme.mycity.R
import com.gyurme.mycity.data.Category
import com.gyurme.mycity.data.RecommendationDetail

object LocalRecommendationDetailProvider {
    private val allRecommendationDetails = listOf(
        RecommendationDetail(
            id = 4L,
            category = Category.Cafe,
            description = R.string.recommendation_detail_cafe_espresso_workshop,
            image = R.drawable.cafe_espresso
        ),
        RecommendationDetail(
            id = 5L,
            category = Category.Restaurant,
            description = R.string.recommendation_detail_restaurant_mexico,
            image = R.drawable.restaurant_mexico
        ),
        RecommendationDetail(
            id = 6L,
            category = Category.Attraction,
            description = R.string.recommendation_detail_attraction_sky_tower,
            image = R.drawable.attraction_sky_tower
        )
    )
}