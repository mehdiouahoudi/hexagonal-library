package com.ouahoudi.library.web;

import com.ouahoudi.library.application.service.OpenLibraryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/external/openlibrary")
public class OpenLibraryController {

    private final OpenLibraryService openLibraryService;

    public OpenLibraryController(OpenLibraryService openLibraryService) {
        this.openLibraryService = openLibraryService;
    }

    @GetMapping
    public String getBooksByBibkeys(@RequestParam String bibkeys) {
        return openLibraryService.fetchBooks(bibkeys);
    }
}

