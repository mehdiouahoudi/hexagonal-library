package com.ouahoudi.library.web;

import com.ouahoudi.library.application.usecase.book.*;
import com.ouahoudi.library.domain.exception.ResourceNotFoundException;
import com.ouahoudi.library.domain.model.Book;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book")
public class DeleteUpdateBookController {

    private final UpdateBookUseCase updateBookUseCase;
    private final DeleteBookUseCase deleteBookUseCase;

    public DeleteUpdateBookController(UpdateBookUseCase updateBookUseCase, DeleteBookUseCase deleteBookUseCase) {
        this.deleteBookUseCase = deleteBookUseCase;
        this.updateBookUseCase = updateBookUseCase;
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<Book> putBook(@PathVariable Long bookId, @Valid @RequestBody Book updateBookRequest) {
    try {
        updateBookUseCase.updateBook(bookId, updateBookRequest);
    }
        catch (ResourceNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long bookId, @Valid @RequestBody Book deleteBookRequest) {

        try {
            deleteBookUseCase.deleteBook(bookId, deleteBookRequest);
        }
        catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
