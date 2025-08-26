package com.gyurme.template.ui

import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue // Keep this for collectAsState
// Add this import for collectAsStateWithLifecycle (recommended)
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun TemplateApp(
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val templateViewModel: TemplateViewModel = hiltViewModel()
    val uiState by templateViewModel.templateUiState.collectAsStateWithLifecycle()
    TemplateScreen(
        templateUiState = uiState,
        onApproveTransaction = templateViewModel::approveTransaction,
        retryAction = templateViewModel::getTransactions
    )
}
