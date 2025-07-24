package com.gyurme.mycity.ui

import androidx.lifecycle.ViewModel
import com.gyurme.mycity.MyCityUiState
import com.gyurme.mycity.data.Category
import com.gyurme.mycity.data.Recommendation
import com.gyurme.mycity.data.local.LocalRecommendationDataProvider
import com.gyurme.mycity.data.local.LocalRecommendationDetailProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MyCityViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MyCityUiState())
    val uiState : StateFlow<MyCityUiState> = _uiState.asStateFlow()

    init {
        initializeUIState()
    }

    fun initializeUIState() {
        val recommendations = LocalRecommendationDataProvider.allRecommendations.groupBy { it.category }
        _uiState.value =
            MyCityUiState(
                allRecommendations = recommendations
            )
    }

    fun setCategory(category: Category) {
       _uiState.update {
            currentState ->
            currentState.copy(
                currentTitle = category.name,
                currentCategory = category,
                selectedRecommendationId = null)
        }
    }

    fun setCurrentRecommendationDetail(recommendation: Recommendation) {
        _uiState.update {
            it.copy(selectedRecommendationId = recommendation.id)
        }
    }
}