package com.gyurme.template.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gyurme.template.data.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TemplateViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository
): ViewModel() {

    var templateUiState: TemplateUiState by mutableStateOf(TemplateUiState.Loading)
        private set

    init {
        getTransactions()
    }

    fun getTransactions() {
        viewModelScope.launch {
            templateUiState = TemplateUiState.Loading
            templateUiState = transactionRepository.getTransactions()
                .fold(
                    onSuccess = { TemplateUiState.Success(it) },
                    onFailure = { TemplateUiState.Error }
                )
        }
    }
}