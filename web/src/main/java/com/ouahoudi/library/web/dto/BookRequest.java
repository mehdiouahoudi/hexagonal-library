package com.ouahoudi.library.web.dto;

import com.ouahoudi.library.domain.model.Author;

import java.time.LocalDate;

public record BookRequest(Long id, String title, LocalDate publicationDate, String type, Author author) {}
