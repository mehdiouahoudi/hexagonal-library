package com.ouahoudi.library.web;

import com.ouahoudi.library.application.usecase.book.CreateBookCommand;
import com.ouahoudi.library.application.usecase.book.CreateBookUseCase;
import com.ouahoudi.library.web.dto.BookRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/book", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class CreateBookController {

    private final CreateBookUseCase createBookUseCase;

    public CreateBookController(CreateBookUseCase createBookUseCase) {
        this.createBookUseCase = createBookUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBook(@RequestBody BookRequest request) {
        createBookUseCase.createBook(new CreateBookCommand(request.id(), request.title(), request.publicationDate(), request.type(), request.author()));
    }
}