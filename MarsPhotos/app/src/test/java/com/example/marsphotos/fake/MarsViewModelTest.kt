package com.example.marsphotos.fake

import com.example.marsphotos.ui.screens.MarsUiState
import com.example.marsphotos.ui.screens.MarsViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import rules.TestDispatcherRule
import kotlin.test.Test
import kotlin.test.assertEquals

class MarsViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun marsViewModel_getMarsPhotos_verifyMarsUiStateSuccess() =
        runTest {
            val marsViewModel = MarsViewModel(
                marsPhotosRepository = FakeNetworkPhotosRepository()
            )
            assertEquals(
                MarsUiState.Success("Success: ${FakeDataSource.photosList.size} Mars " + "photos retrieved"),
                marsViewModel.marsUiState
            )
        }
}