package com.vheekey.book_service.common;

import com.vheekey.book_service.documents.Book;
import com.vheekey.book_service.enums.BookStatus;
import com.vheekey.book_service.requests.BookRequest;
import com.vheekey.book_service.requests.UpdateBookRequest;
import com.vheekey.book_service.responses.BookResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class BookMapper {
    public BookResponse toResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .description(book.getDescription())
                .category(book.getCategory())
                .fine(book.getFine())
                .location(book.getLocation())
                .isbn(book.getIsbn())
                .type(book.getType())
                .status(book.getStatus())
                .createdAt(book.getCreatedAt())
                .updatedAt(book.getUpdatedAt())
                .build();
    }

    public Book toEntity(BookRequest request) {
        Book book = new Book();
        book.setId(UUID.randomUUID().toString());
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setDescription(request.getDescription());
        book.setLocation(request.getLocation());
        book.setCategory(request.getCategory());
        book.setIsbn(request.getIsbn());
        book.setType(request.getBookType());
        book.setFine(BigDecimal.valueOf(request.getFine()));
        book.setStatus(BookStatus.AVAILABLE);// Setting default status

        return book;
    }

    public Book updateBook(Book book, UpdateBookRequest request) {
        if (request.getTitle() != null) {
            book.setTitle(request.getTitle());
        }

        if (request.getAuthor() != null) {
            book.setAuthor(request.getAuthor());
        }

        if (request.getDescription() != null) {
            book.setDescription(request.getDescription());
        }

        if (request.getLocation() != null) {
            book.setLocation(request.getLocation());
        }

        if (request.getBookType() != null) {
            book.setType(request.getBookType());
        }

        if (request.getCategory() != null) {
            book.setCategory(request.getCategory());
        }

        if (request.getIsbn() != null) {
            book.setIsbn(request.getIsbn());
        }

        if (request.getFine() != null && request.getFine() > 0) {
            book.setFine(BigDecimal.valueOf(request.getFine()));
        }

        return book;
    }
}
