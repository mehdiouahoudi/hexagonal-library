package com.ouahoudi.library.domain.model;

public enum BookType {
    ROMAN("Roman"),
    ESSAI("Essai"),
    SCIENCE_FICTION("Science-fiction"),
    NON_FICTION("Non-fiction"),
    FICTION("Fiction");

    private final String label;

    BookType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
