package com.ouahoudi.library.web;

import com.ouahoudi.library.application.usecase.book.FindBookByIdCommand;
import com.ouahoudi.library.application.usecase.book.FindBookByIdUseCase;
import com.ouahoudi.library.application.usecase.book.FindBookByTitleUseCase;
import com.ouahoudi.library.application.usecase.book.FindBookByTitleCommand;
import com.ouahoudi.library.domain.model.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/book")
public class FindBookController {

    private final FindBookByTitleUseCase findBookUseCase;
    private final FindBookByIdUseCase findBookByIdUseCase;

    public FindBookController(FindBookByTitleUseCase findBookUseCase, FindBookByIdUseCase findBookByIdUseCase) {
        this.findBookUseCase = findBookUseCase;
        this.findBookByIdUseCase = findBookByIdUseCase;
    }

    @GetMapping("/title")
    public ResponseEntity<Book> getByTitle(@RequestParam("title") String title) {
        Optional<Book> result = findBookUseCase.findBookByTitle(new FindBookByTitleCommand(title));
        return result.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/id")
    public ResponseEntity<Book> getById(@RequestParam("id") Long id) {
        Optional<Book> result = findBookByIdUseCase.findBookById(new FindBookByIdCommand(id));
        return result.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
