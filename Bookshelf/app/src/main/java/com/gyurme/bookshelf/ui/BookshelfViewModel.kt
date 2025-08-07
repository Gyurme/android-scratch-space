package com.gyurme.bookshelf.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.gyurme.bookshelf.data.Book
import com.gyurme.bookshelf.data.BookShelfRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class BookshelfViewModel @Inject constructor(
    private val bookShelfRepository: BookShelfRepository
) : ViewModel() {

    var bookshelfUiState: BookshelfUiState by mutableStateOf(BookshelfUiState.Loading)
        private set

    init {
        getBooks()
    }

    fun getBooks() {
        viewModelScope.launch {
            bookshelfUiState = BookshelfUiState.Loading
            bookshelfUiState = try {
                val result = bookShelfRepository.getBooksForCategory("jazz")
                val books =
                    result.map { book ->
                        val details = bookShelfRepository.getBookDetails(book.id)
                        Book(id = book.id, imgSrc = details.imageLinks.smallThumbnail)
                    }
                BookshelfUiState.Success(books)
            } catch (e: IOException) {
                BookshelfUiState.Error
            } catch (e: HttpException) {
                BookshelfUiState.Error
            }
        }
    }
}