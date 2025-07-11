package com.ouahoudi.library.application.service;

import com.ouahoudi.library.application.usecase.book.GiveBookScoringUseCase;
import com.ouahoudi.library.domain.model.BookScoring;
import com.ouahoudi.library.domain.model.port.out.book.BookScoringRulePort;

import java.time.LocalDate;
import java.util.Optional;

public class BookScoringService implements GiveBookScoringUseCase {

    protected final BookScoringRulePort bookScoringRule;

    public BookScoringService(BookScoringRulePort bookScoringRulePort) {
        this.bookScoringRule = bookScoringRulePort;

    }
    public Optional<BookScoring> scoreBook(Long bookId, LocalDate publicationDate, String type, int authorAge, int authorFollowersNumber) {

        return  bookScoringRule.score(bookId, publicationDate, type, authorAge, authorFollowersNumber);
    }
}
