package com.gyurme.mycity.data.local

import com.gyurme.mycity.R
import com.gyurme.mycity.data.Recommendation
import com.gyurme.mycity.data.RecommendationDetail

object LocalRecommendationDetailProvider {
    private val allRecommendationDetails = listOf(
        RecommendationDetail(
            id = 4L,
            recommendation = LocalRecommendationDataProvider.getRecommendationById(1L),
            description = R.string.recommendation_detail_cafe_espresso_workshop
        ),
        RecommendationDetail(
            id = 5L,
            recommendation = LocalRecommendationDataProvider.getRecommendationById(2L),
            description = R.string.recommendation_detail_restaurant_mexico
        ),
        RecommendationDetail(
            id = 6L,
            recommendation = LocalRecommendationDataProvider.getRecommendationById(3L),
            description = R.string.recommendation_detail_attraction_sky_tower
        )
    )

    val defaultRecommendationDetail = allRecommendationDetails[0]

    fun getDetailForRecommendation(recommendation: Recommendation) : RecommendationDetail {
        return allRecommendationDetails.find { it.recommendation.id == recommendation.id } ?: defaultRecommendationDetail
    }
}