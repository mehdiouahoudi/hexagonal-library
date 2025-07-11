package com.ouahoudi.library.application.usecase.book;

import com.ouahoudi.library.domain.model.Book;

import java.util.Optional;

public interface FindBookByIdUseCase {
    Optional<Book> findBookById(FindBookByIdCommand command);
}
