package com.ouahoudi.library.infrastructure.adapter;

import static org.junit.jupiter.api.Assertions.*;

import com.ouahoudi.library.domain.model.BookScoring;
import com.ouahoudi.library.domain.model.port.out.book.BookScoringRulePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

class BookScoringRuleAdapterTest {

    private BookScoringRulePort scoringRuleAdapter;

    @BeforeEach
    void setUp() {
        scoringRuleAdapter = new BookScoringRuleAdapter();
    }

    @Test
    void score_shouldComputeExpectedScore() {
        // Given
        Long bookId = 1L;
        LocalDate publicationDate = LocalDate.now().minusYears(2); // publication il y a 2 ans
        String type = "FICTION";
        int authorAge = 35;
        int followers = 100_000;

        // When
        Optional<BookScoring> result = scoringRuleAdapter.score(bookId, publicationDate, type, authorAge, followers);

        // Then
        assertTrue(result.isPresent());
        double expectedDateScore = 5 - 0.5 * 2; // 4.0
        double expectedAgeScore = 2.5 - 0.05 * (35 - 30); // 2.25
        double maxLog = Math.log10(1_000_000);
        double expectedFollowerScore = 2.5 * Math.log10(100_000 + 1) / maxLog;
        double expectedTotalScore = expectedDateScore + expectedAgeScore + expectedFollowerScore;

        assertEquals(expectedTotalScore, result.get().getScore(), 0.0001);
    }

    @Test
    void score_shouldComputeMaxExpectedScore() {
        // Given
        Long bookId = 2L;
        LocalDate publicationDate = LocalDate.now().minusYears(0); // publication il y a 2 ans
        String type = "ESSAI";
        int authorAge = 24;
        int followers = 1_000_000;

        // When
        Optional<BookScoring> result = scoringRuleAdapter.score(bookId, publicationDate, type, authorAge, followers);

        // Then
        assertTrue(result.isPresent());
        double expectedDateScore = 5 - 0.5 * 0; // 5.0
        double expectedAgeScore = 2.5 - 0.05 * (24 - 30); // 2.5
        double maxLog = Math.log10(1_000_000);
        double expectedFollowerScore = 2.5 * Math.log10(100_000 + 1) / maxLog; // 2.5
        double expectedTotalScore = expectedDateScore + expectedAgeScore + expectedFollowerScore;

        assertEquals(expectedTotalScore, result.get().getScore(), 1);
    }

    @Test
    void computeDateScore_shouldClampToZeroWhenTooOld() {
        LocalDate veryOldDate = LocalDate.now().minusYears(20);
        double score = scoringRuleAdapter.computeDateScore(veryOldDate);
        assertEquals(0, score);
    }

    @Test
    void computeDateScore_shouldClampToFiveWhenRecent() {
        LocalDate thisYear = LocalDate.now();
        double score = scoringRuleAdapter.computeDateScore(thisYear);
        assertEquals(5.0, score);
    }

    @Test
    void computeAgeScore_shouldClampToZeroWhenOld() {
        double score = scoringRuleAdapter.computeAgeScore(100);
        assertEquals(0, score);
    }

    @Test
    void computeAgeScore_shouldClampToMaxWhenYoung() {
        double score = scoringRuleAdapter.computeAgeScore(25);
        assertEquals(2.5, score);
    }

    @Test
    void computeFollowersScore_shouldBeZeroWhenNoFollowers() {
        double score = scoringRuleAdapter.computeFollowersScore(0);
        assertEquals(0, score);
    }

    @Test
    void computeFollowersScore_shouldClampToMaxAtMillionFollowers() {
        double score = scoringRuleAdapter.computeFollowersScore(1_000_000);
        assertEquals(2.5, score, 0.001);
    }

    @Test
    void computeFollowersScore_shouldIncreaseWithFollowers() {
        double score1 = scoringRuleAdapter.computeFollowersScore(100);
        double score2 = scoringRuleAdapter.computeFollowersScore(10_000);
        assertTrue(score2 > score1);
    }
}
