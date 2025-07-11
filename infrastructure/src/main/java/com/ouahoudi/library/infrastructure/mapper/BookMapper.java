package com.ouahoudi.library.infrastructure.mapper;

import com.ouahoudi.library.domain.model.Book;
import com.ouahoudi.library.infrastructure.entity.BookEntity;

public class BookMapper {

    private BookMapper() {
    }

    public static Book toDomain(BookEntity entity) {
        return new Book(entity.getId(), entity.getTitle(), entity.getPublicationDate(), entity.getType());
    }

    public static BookEntity toEntity(Book book) {
        return new BookEntity(book.getId(), book.getTitle(), book.getPublicationDate(), book.getType(), AuthorMapper.toEntity(book.getAuthor()));
    }
}

