package com.ouahoudi.library.application.usecase.book;

import com.ouahoudi.library.domain.model.Author;

import java.time.LocalDate;

public record CreateBookCommand(Long id, String title, LocalDate publicationDate, String type, Author author) {}
