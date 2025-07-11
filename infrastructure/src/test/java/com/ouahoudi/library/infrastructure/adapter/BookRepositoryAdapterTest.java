package com.ouahoudi.library.infrastructure.adapter;

import com.ouahoudi.library.domain.model.Author;
import com.ouahoudi.library.domain.model.Book;
import com.ouahoudi.library.domain.model.BookType;
import com.ouahoudi.library.infrastructure.entity.BookEntity;
import com.ouahoudi.library.infrastructure.mapper.BookMapper;
import com.ouahoudi.library.infrastructure.repository.JpaAuthorRepository;
import com.ouahoudi.library.infrastructure.repository.JpaBookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookRepositoryAdapterTest {
    @Mock
    private JpaBookRepository jpaBookRepository;

    @Mock
    private JpaAuthorRepository jpaAuthorRepository;

    @InjectMocks
    private BookRepositoryAdapter adapter;

    private Book existingBook;
    private Book updateRequest;

    @BeforeEach
    void setUp() {
        existingBook = new Book(1L, "Old Title", LocalDate.of(2000, 1, 1), BookType.ROMAN.getLabel(), new Author(1L, "Joe", 50, 10000));
        updateRequest = new Book(1L, "New Title", LocalDate.of(2020, 5, 20), BookType.ESSAI.getLabel(),  new Author(1L, "Joe", 50, 10000));
    }

    @Test
    void update_shouldUpdateBookAndReturnEmptyOptional() {
        // Given
        when(jpaBookRepository.findById(1L)).thenReturn(Optional.of(BookMapper.toEntity(existingBook)));
        when(jpaBookRepository.save(any(BookEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // When
        Optional<Book> result = adapter.update(existingBook, updateRequest);
//
        // Then
        assertTrue(result.isPresent());
//        verify(jpaBookRepository).findById(1L);
//        verify(jpaBookRepository).save(argThat(book ->
//                book.getTitle().equals("New Title")
//                        && book.getPublicationDate().equals(LocalDate.of(2020, 5, 20))
//                        && Objects.equals(book.getType(), BookType.ESSAI.getLabel())
//        ));
        // Act
        Optional<Book> updatedBookOpt = adapter.update(existingBook, updateRequest);

        // Assert
        assertTrue(updatedBookOpt.isPresent());
        Book updatedBook = updatedBookOpt.get();
        assertEquals("New Title", updatedBook.getTitle());
        assertEquals(LocalDate.of(2020, 5, 20), updatedBook.getPublicationDate());
        assertEquals(BookType.ESSAI.getLabel(), updatedBook.getType());

        // Verify interactions avec le mock
        verify(jpaBookRepository, times(2)).findById(1L);

        updatedBook.setAuthor(new Author(1L, "Joe", 50, 10000));
        verify(jpaBookRepository, times(2)).save(any(BookEntity.class));
        //verify(jpaAuthorRepository, times(1)).findByName(updatedBook.getAuthor().getName());
        verifyNoMoreInteractions(jpaBookRepository);
    }
}