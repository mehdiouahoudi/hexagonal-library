package com.ouahoudi.library.infrastructure.repository;

import com.ouahoudi.library.infrastructure.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaBookRepository extends JpaRepository<BookEntity, Long> {
    Optional<BookEntity> findByTitle(String title);
}
