package com.ouahoudi.library.application.usecase.book;

import com.ouahoudi.library.domain.model.Book;

public interface DeleteBookUseCase {

    void deleteBook(Long bookId, Book deleteBookRequest);
}
