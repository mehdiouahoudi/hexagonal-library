package com.ouahoudi.library.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "authors") public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id") private Long id;

    @Column(name = "author_name") private String name;

    @Column(name = "author_age") private int age;

    @Column(name = "book_followers_number") private int followersNumber;

    public AuthorEntity(String name, int age, int followersNumber) {
        this.name = name;
        this.age = age;
        this.followersNumber = followersNumber;
    }
}
