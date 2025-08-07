package com.gyurme.bookshelf.ui

import com.gyurme.bookshelf.data.Book

sealed interface BookshelfUiState {
    data class Success(val books: List<Book>) : BookshelfUiState
    object Loading : BookshelfUiState
    object Error : BookshelfUiState
}