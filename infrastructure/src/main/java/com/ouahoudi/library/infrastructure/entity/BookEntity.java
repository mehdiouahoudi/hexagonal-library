package com.ouahoudi.library.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity @Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;
    @Column(name = "book_title")
    private String title;
    @Column(name = "book_publication_date")
    private LocalDate publicationDate;
    @Column(name = "book_type")
    private String type;

    public BookEntity(String title, LocalDate publicationDate, String type) {
        this.title = title;
        this.publicationDate = publicationDate;
        this.type = type;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "author_id", referencedColumnName = "author_id")
    private AuthorEntity author;
}
