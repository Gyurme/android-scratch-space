package com.gyurme.template.ui

// Remove: import androidx.compose.runtime.getValue
// Remove: import androidx.compose.runtime.mutableStateOf
// Remove: import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gyurme.template.data.TransactionRepository
// Add these imports for StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TemplateViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository
) : ViewModel() {

    // Change from mutableStateOf to MutableStateFlow
    private val _templateUiState = MutableStateFlow<TemplateUiState>(TemplateUiState.Loading)
    val templateUiState: StateFlow<TemplateUiState> = _templateUiState.asStateFlow()
    // The 'private set' is no longer needed as the public property is now immutable (StateFlow)

    init {
        getTransactions()
    }

    fun getTransactions() {
        viewModelScope.launch {
            _templateUiState.value = TemplateUiState.Loading
            transactionRepository.getTransactions()
                .fold(
                    onSuccess = { transactions ->
                        _templateUiState.value = TemplateUiState.Success(transactions)
                    },
                    onFailure = {
                        _templateUiState.value = TemplateUiState.Error
                    }
                )
        }
    }

    fun approveTransaction(id: Long) {
        viewModelScope.launch {
            transactionRepository.approveTransaction(id)
                .fold(
                    onSuccess = {
                        getTransactions()
                    },
                    onFailure = {
                        _templateUiState.value = TemplateUiState.Error
                    }
                )
        }
    }
}