package com.ouahoudi.library.application.usecase.book;

import com.ouahoudi.library.domain.model.Book;

public interface UpdateBookUseCase {
    void updateBook(Long bookId, Book bookUpdateRequest);
}
