package com.ouahoudi.library.domain.model.port.out.book;

import com.ouahoudi.library.domain.model.Book;

import java.util.Optional;

public interface BookRepositoryPort {
    Book save(Book book);
    Optional<Book> findById(Long id);
    Optional<Book> findByTitle(String title);
    Optional<Book> update(Book book, Book updateBookRequest);
    Optional<Book> delete(Book book);
}

