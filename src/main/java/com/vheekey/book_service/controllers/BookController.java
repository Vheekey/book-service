package com.vheekey.book_service.controllers;

import com.vheekey.book_service.requests.BookRequest;
import com.vheekey.book_service.responses.BookResponse;
import com.vheekey.book_service.services.interfaces.BookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/books")
@Validated
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping()
    public ResponseEntity<BookResponse> create(@Valid @RequestBody BookRequest request) {
        BookResponse response = this.bookService.addBook(request);

        return ResponseEntity.ok(response);
    }
}
