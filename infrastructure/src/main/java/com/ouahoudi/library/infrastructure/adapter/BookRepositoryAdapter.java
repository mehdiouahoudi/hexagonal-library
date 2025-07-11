package com.ouahoudi.library.infrastructure.adapter;

import com.ouahoudi.library.domain.model.Author;
import com.ouahoudi.library.domain.model.Book;
import com.ouahoudi.library.domain.model.port.out.book.BookRepositoryPort;
import com.ouahoudi.library.infrastructure.entity.AuthorEntity;
import com.ouahoudi.library.infrastructure.entity.BookEntity;
import com.ouahoudi.library.infrastructure.mapper.BookMapper;
import com.ouahoudi.library.infrastructure.repository.JpaAuthorRepository;
import com.ouahoudi.library.infrastructure.repository.JpaBookRepository;

import java.util.Optional;

public class BookRepositoryAdapter implements BookRepositoryPort {

    private final JpaBookRepository jpaBookRepo;
    private final JpaAuthorRepository jpaAuthorRepo;

    public BookRepositoryAdapter(JpaBookRepository jpaBookRepo, JpaAuthorRepository jpaAuthorRepo) {
        this.jpaBookRepo = jpaBookRepo;
        this.jpaAuthorRepo = jpaAuthorRepo;
    }

    @Override
    public Optional<Book> update(Book book, Book updateBookRequest) {

        return jpaBookRepo.findById(book.getId())
                .map(existingBook -> {
                    existingBook.setTitle(updateBookRequest.getTitle());
                    existingBook.setPublicationDate(updateBookRequest.getPublicationDate());
                    existingBook.setType(updateBookRequest.getType());

                    return BookMapper.toDomain(jpaBookRepo.save(existingBook));
                });
    }

    @Override
    public Book save(Book book) {
        BookEntity entity = new BookEntity(book.getTitle(), book.getPublicationDate(), book.getType());
        AuthorEntity aEntity = new AuthorEntity(book.getAuthor().getName(), book.getAuthor().getAge(), book.getAuthor().getFollowersNumber());

        if (jpaAuthorRepo.findByName(book.getAuthor().getName()).isEmpty()) {
            entity.setAuthor(aEntity);
            jpaAuthorRepo.save(aEntity);
        }
        else {
            entity.setAuthor(jpaAuthorRepo.findByName(book.getAuthor().getName()).orElse(null));
        }
        BookEntity saved = jpaBookRepo.save(entity);
        return new Book(saved.getId(), saved.getTitle(), saved.getPublicationDate(), saved.getType());
    }

    @Override
    public Optional<Book> findById(Long id) {
        Optional<BookEntity> found = jpaBookRepo.findById(id);
        return found.map(entity ->  new Book(entity.getId(), entity.getTitle(), entity.getPublicationDate(), entity.getType(), new Author(entity.getAuthor().getId(), entity.getAuthor().getName(), entity.getAuthor().getAge(), entity.getAuthor().getFollowersNumber())));

    }

    @Override
    public Optional<Book> findByTitle(String title) {
        Optional<BookEntity> found = jpaBookRepo.findByTitle(title);
        return found.map(entity -> new Book(entity.getId(), entity.getTitle(), entity.getPublicationDate(), entity.getType()));
    }



    @Override
    public Optional<Book> delete(Book book) {
        jpaBookRepo.findById(book.getId()).ifPresent(jpaBookRepo::delete);
        return Optional.empty();
    }
}
