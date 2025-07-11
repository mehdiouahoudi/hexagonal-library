package com.ouahoudi.library.application.service;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class OpenLibraryService {

    private final RestTemplate restTemplate = new RestTemplate();

    public String fetchBooks(String bibkeys) {
        String url = UriComponentsBuilder.fromHttpUrl("https://openlibrary.org/api/books")
                .queryParam("bibkeys", bibkeys)
                .queryParam("format", "json")
                .toUriString();

        return restTemplate.getForObject(url, String.class);
    }
}

