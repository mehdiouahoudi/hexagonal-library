package com.ouahoudi.library.web.dto;

import java.time.LocalDate;

public class BookResponse {

    private String id;
    private String title;
    private LocalDate publicationDate;
    private String type;

    public BookResponse() {
    }

    public BookResponse(String id, String title, LocalDate publicationDate, String type) {
        this.id = id;
        this.title = title;
        this.publicationDate = publicationDate;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public String getType() {
        return type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setType(String type) {
        this.type = type;
    }
}
