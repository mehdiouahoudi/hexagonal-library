package com.ouahoudi.library.application.service;

import com.ouahoudi.library.application.usecase.book.*;
import com.ouahoudi.library.domain.exception.ResourceNotFoundException;
import com.ouahoudi.library.domain.model.Author;
import com.ouahoudi.library.domain.model.Book;
import com.ouahoudi.library.domain.model.port.out.book.BookRepositoryPort;

import java.util.*;
import java.util.stream.Collectors;

public class BookService implements CreateBookUseCase, FindBookByTitleUseCase, UpdateBookUseCase, FindBookByIdUseCase, DeleteBookUseCase, FindAuthorByBookIdsUseCase {

    protected final BookRepositoryPort bookRepository;

    public BookService(BookRepositoryPort bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void createBook(CreateBookCommand command) {
        Author author = new Author(command.author().getId(), command.author().getName(), command.author().getAge(), command.author().getFollowersNumber());
        Book book = new Book(command.id(), command.title(), command.publicationDate(), command.type());

        book.setAuthor(author);
        bookRepository.save(book);
    }

    @Override
    public Optional<Book> findBookByTitle(FindBookByTitleCommand command) {
        return bookRepository.findByTitle(command.title());
    }

    @Override
    public Optional<Book> findBookById(FindBookByIdCommand command) {
        return bookRepository.findById(command.id());
    }

    @Override
    public void updateBook(Long bookId, Book bookUpdateRequest) {
        Book existingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + bookId));

        bookRepository.update(existingBook, bookUpdateRequest);
    }

    @Override
    public void deleteBook(Long bookId, Book deleteBookRequest) {
        bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + bookId));

        bookRepository.delete(deleteBookRequest);
    }

    @Override
    public List<Author> findAuthorsByBookIds(List<Long> bookIds) {

        if (bookIds == null || bookIds.isEmpty()) {
            return Collections.emptyList();
        }

        return bookIds.stream()
                .map(this::get)
                .map(Book::getAuthor)
                .filter(Objects::nonNull)
                .collect(Collectors.collectingAndThen(
                        Collectors.toMap(Author::getId, a -> a, (a1, a2) -> a1),
                        m -> new ArrayList<>(m.values())
                ));
    }

    public Book get(Long bookId) {
        return bookRepository.findById(bookId).map(entity ->  new Book(entity.getId(), entity.getTitle(), entity.getPublicationDate(), entity.getType(), new Author(entity.getAuthor().getId(), entity.getAuthor().getName(), entity.getAuthor().getAge(), entity.getAuthor().getFollowersNumber())))
                .orElseThrow(() -> new ResourceNotFoundException("Book by ID '" + bookId + "' not found."));
    }
}
