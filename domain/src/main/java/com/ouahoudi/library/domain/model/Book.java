package com.ouahoudi.library.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class Book {
    private Long id;
    private String title;
    private LocalDate publicationDate;
    private String type;

    private Author author;

    @JsonCreator
    public Book(
            @JsonProperty("id") Long id,
            @JsonProperty("title") String title,
            @JsonProperty("publicationDate") LocalDate publicationDate,
            @JsonProperty("type") String type) {
        this.id = id;
        this.title = title;
        this.publicationDate = publicationDate;
        this.type = type;
    }

    @JsonCreator
    public Book(
            @JsonProperty("id") Long id,
            @JsonProperty("title") String title,
            @JsonProperty("publicationDate") LocalDate publicationDate,
            @JsonProperty("type") String type,
            @JsonProperty("author") Author author) {
        this.id = id;
        this.title = title;
        this.publicationDate = publicationDate;
        this.type = type;
        this.author = author;
    }

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
