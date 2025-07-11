package com.ouahoudi.library.application.usecase.book;

import com.ouahoudi.library.domain.model.BookScoring;

import java.time.LocalDate;
import java.util.Optional;

public interface GiveBookScoringUseCase {
    Optional<BookScoring> scoreBook(Long bookId, LocalDate publicationDate, String type, int authorAge, int authorFollowersNumber);
}
