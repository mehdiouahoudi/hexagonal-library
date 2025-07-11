package com.ouahoudi.library.infrastructure.adapter;

import com.ouahoudi.library.domain.model.BookScoring;
import com.ouahoudi.library.domain.model.port.out.book.BookScoringRulePort;

import java.time.LocalDate;
import java.util.Optional;

public class BookScoringRuleAdapter implements BookScoringRulePort {

    @Override
    public Optional<BookScoring> score(Long bookId, LocalDate publicationDate, String type, int authorAge,  int authorFollowersNumber) {
        double scoreDate = computeDateScore(publicationDate);
        double scoreAge = computeAgeScore(authorAge);
        double scoreFollowers = computeFollowersScore(authorFollowersNumber);

        double totalScore = scoreDate + scoreAge + scoreFollowers;
        return Optional.of(new BookScoring(totalScore));
    }

    public double computeDateScore(LocalDate publicationDate) {
        int currentYear = LocalDate.now().getYear();
        int publicationYear = publicationDate.getYear();
        return Math.max(0, Math.min(5, 5 - 0.5 * (currentYear - publicationYear)));
    }

    public double computeAgeScore(int age) {
        return Math.max(0, Math.min(2.5, 2.5 - 0.05 * (age - 30)));
    }

    public double computeFollowersScore(long followers) {
        if (followers <= 0) return 0;
        double maxLog = Math.log10(1_000_000);
        double followerScore = 2.5 * Math.log10((double) followers + 1) / maxLog;
        return Math.min(2.5, followerScore);
    }
}
