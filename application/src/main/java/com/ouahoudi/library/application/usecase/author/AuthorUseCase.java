package com.ouahoudi.library.application.usecase.author;

import com.ouahoudi.library.domain.model.Author;

import java.util.List;

public interface AuthorUseCase {

    List<Author> findAuthorByBookIds(List<Long> bookIds);
}
