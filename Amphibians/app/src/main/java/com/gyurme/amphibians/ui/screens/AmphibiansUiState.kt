package com.gyurme.amphibians.ui.screens

import com.gyurme.amphibians.network.Amphibian

sealed interface AmphibiansUiState {
    data class Success(val amphibians: List<Amphibian>) : AmphibiansUiState
    object Error : AmphibiansUiState
    object Loading : AmphibiansUiState
}