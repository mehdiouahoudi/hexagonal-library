package com.ouahoudi.library.web;

import com.ouahoudi.library.application.usecase.book.FindAuthorByBookIdsUseCase;
import com.ouahoudi.library.domain.model.Author;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/author")
public class FindAuthorController {

    private final FindAuthorByBookIdsUseCase findAuthorByBookIdsUseCase;

    public FindAuthorController(FindAuthorByBookIdsUseCase findAuthorByBookIdsUseCase) {
        this.findAuthorByBookIdsUseCase = findAuthorByBookIdsUseCase;
    }


    @GetMapping("/ids")
    public ResponseEntity<List<Author>> getByBookIds(@RequestParam("bookIds") List<Long> ids) {

        List<Author> result = findAuthorByBookIdsUseCase.findAuthorsByBookIds(ids);

        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);

    }
}
