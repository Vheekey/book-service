package com.vheekey.book_service.controllers;

import com.vheekey.book_service.requests.BookRequest;
import com.vheekey.book_service.requests.UpdateBookRequest;
import com.vheekey.book_service.responses.BookResponse;
import com.vheekey.book_service.services.interfaces.BookService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        BookResponse response = bookService.addBook(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<Page<BookResponse>> search(@Valid @PathVariable String keyword,
                                                     @RequestParam(defaultValue = "0") int pageNumber,
                                                     @RequestParam(defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<BookResponse> bookResponse = bookService.searchBookWildCard(keyword, pageable);

        return ResponseEntity.ok(bookResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> update(@PathVariable String id, @Valid @RequestBody UpdateBookRequest request) {
        BookResponse response = bookService.updateBook(id, request);

        return ResponseEntity.ok(response);
    }
}
