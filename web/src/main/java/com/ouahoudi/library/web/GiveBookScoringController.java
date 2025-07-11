package com.ouahoudi.library.web;

import com.ouahoudi.library.application.usecase.book.FindBookByIdCommand;
import com.ouahoudi.library.application.usecase.book.FindBookByIdUseCase;
import com.ouahoudi.library.application.usecase.book.GiveBookScoringUseCase;
import com.ouahoudi.library.domain.model.Book;
import com.ouahoudi.library.domain.model.BookScoring;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/book")
public class GiveBookScoringController {

    private final GiveBookScoringUseCase scoreBookUseCase;
    private final FindBookByIdUseCase findBookByIdUseCase;

    public GiveBookScoringController(GiveBookScoringUseCase scoreBookUseCase, FindBookByIdUseCase findBookByIdUseCase) {
        this.scoreBookUseCase = scoreBookUseCase;
        this.findBookByIdUseCase = findBookByIdUseCase;
    }

    @GetMapping("/{id}/score")
    public ResponseEntity<BookScoring> getBookScore(@PathVariable Long id) {
        Optional<Book> existingBook = findBookByIdUseCase.findBookById(new FindBookByIdCommand(id));

        Optional<BookScoring> score ;

        if (existingBook.isPresent()) {
             score = scoreBookUseCase.scoreBook(id, existingBook.get().getPublicationDate(), existingBook.get().getType(), existingBook.get().getAuthor().getAge(), existingBook.get().getAuthor().getFollowersNumber());
        }
        else{
            score = Optional.of(new BookScoring(0));
        }
        return score.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }
}
