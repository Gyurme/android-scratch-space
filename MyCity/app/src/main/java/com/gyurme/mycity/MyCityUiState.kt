package com.gyurme.mycity

import com.gyurme.mycity.data.Category
import com.gyurme.mycity.data.Recommendation
import com.gyurme.mycity.data.RecommendationDetail
import com.gyurme.mycity.data.local.LocalRecommendationDetailProvider

data class MyCityUiState(
    val allRecommendations : Map<Category, List<Recommendation>> = emptyMap(),
    val currentCategory: Category = Category.Cafe,
    val currentRecommendationDetail: RecommendationDetail = LocalRecommendationDetailProvider.defaultRecommendationDetail,
   val currentTitle: String = ""
) {
    val currentRecommendations : List<Recommendation> by lazy { allRecommendations[currentCategory]!! }
}
