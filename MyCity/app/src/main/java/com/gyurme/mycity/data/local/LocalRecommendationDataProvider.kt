package com.gyurme.mycity.data.local

import com.gyurme.mycity.R
import com.gyurme.mycity.data.Category
import com.gyurme.mycity.data.Recommendation

object LocalRecommendationDataProvider {
     val allRecommendations = listOf(
        Recommendation(
            id = 1L,
            Category.Cafe,
            R.string.recommendation_cafe_1,
            image = R.drawable.detail_cafe_espresso
        ),
        Recommendation(
            id = 2L,
            Category.Restaurant,
            R.string.recommendation_restaurant_1,
            image = R.drawable.detail_restaurant_mexico
        ),
        Recommendation(
            id = 3L,
            Category.Attraction,
            R.string.recommendation_attraction_1,
            image = R.drawable.detail_attraction_sky
        ),
    )

    fun getRecommendationById(id: Long) : Recommendation {
        return allRecommendations.firstOrNull { it.id == id } ?: allRecommendations.first()
    }
}