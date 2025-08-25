package com.gyurme.template.ui

import com.gyurme.template.data.Transaction

sealed interface TemplateUiState {
    data class Success(val transactions: List<Transaction>) : TemplateUiState
    object Loading : TemplateUiState
    object Error : TemplateUiState
}