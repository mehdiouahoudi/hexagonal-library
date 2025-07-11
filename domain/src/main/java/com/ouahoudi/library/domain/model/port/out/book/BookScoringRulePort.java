package com.ouahoudi.library.domain.model.port.out.book;

import com.ouahoudi.library.domain.model.BookScoring;

import java.time.LocalDate;
import java.util.Optional;

public interface BookScoringRulePort {
    Optional<BookScoring> score(Long bookId, LocalDate publicationDate, String type, int authorAge, int authorFollowersNumber);
    double computeDateScore(LocalDate publicationDate);
    double computeAgeScore(int age);
    double computeFollowersScore(long followers);
}
