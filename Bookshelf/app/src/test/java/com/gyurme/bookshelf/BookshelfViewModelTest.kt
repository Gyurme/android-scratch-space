package com.gyurme.bookshelf

import com.gyurme.bookshelf.data.Book
import com.gyurme.bookshelf.data.BookShelfRepository
import com.gyurme.bookshelf.network.BookDetails
import com.gyurme.bookshelf.network.BookDto
import com.gyurme.bookshelf.network.ImageInfo
import com.gyurme.bookshelf.ui.BookshelfUiState
import com.gyurme.bookshelf.ui.BookshelfViewModel
import com.gyurme.bookshelf.utils.TestDispatcherRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import junit.framework.TestCase.fail
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.Rule


class BookshelfViewModelTest {

    @MockK
    lateinit var repository: BookShelfRepository

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Before
    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true)

    val bookDetails = BookDetails(
        imageLinks = ImageInfo(
            smallThumbnail = "http://small",
            thumbnail = "http://thumbnail"
        )
    )

    val bookDto = BookDto(
        id = "id",
        volumeInfo = bookDetails
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when the viewmodel is initialized, book information is requested from the repository and the uiState is updated`() {
        runTest {
            coEvery { repository.getBooksForCategory("jazz") } returns listOf(
                bookDto
            )
            coEvery { repository.getBookDetails("id") } returns bookDetails
            val bookshelfViewModel = BookshelfViewModel(bookShelfRepository = repository)

            advanceUntilIdle()

            when (val state = bookshelfViewModel.bookshelfUiState) {
                is BookshelfUiState.Success -> {
                    assertEquals(1, state.books.size)
                    assertEquals(bookDto.id, state.books.first().id)
                    assertEquals(bookDetails.imageLinks.smallThumbnail, state.books.first().imgSrc)
                }
                else -> fail("Expected Success state but was $state")
            }
        }
    }
}