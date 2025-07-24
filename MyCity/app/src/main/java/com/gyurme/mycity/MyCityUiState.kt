package com.gyurme.mycity

import com.gyurme.mycity.data.Category
import com.gyurme.mycity.data.Recommendation
import com.gyurme.mycity.data.RecommendationDetail
import com.gyurme.mycity.data.local.LocalRecommendationDetailProvider

data class MyCityUiState(
    val allRecommendations: Map<Category, List<Recommendation>> = emptyMap(),
    val currentCategory: Category = Category.Cafe,
    val selectedRecommendationId: Long? = null,
    val currentTitle: String = ""
) {
    val currentRecommendations: List<Recommendation>
        get() = allRecommendations[currentCategory] ?: emptyList()

    val currentRecommendationDetail: RecommendationDetail
        get() {
            val targetRecommendation = when {
                // If something is selected, use it
                selectedRecommendationId != null -> {
                    currentRecommendations.find { it.id == selectedRecommendationId }
                }
                // Otherwise, default to first recommendation in current category
                else -> currentRecommendations.firstOrNull()
            }

            return targetRecommendation?.let {
                LocalRecommendationDetailProvider.getDetailForRecommendation(it)
            } ?: LocalRecommendationDetailProvider.defaultRecommendationDetail
        }
}
