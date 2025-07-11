package com.ouahoudi.library.application.usecase.book;

import com.ouahoudi.library.domain.model.Author;

import java.util.List;

public interface FindAuthorByBookIdsUseCase {
    List<Author> findAuthorsByBookIds(List<Long> bookIds);
}
