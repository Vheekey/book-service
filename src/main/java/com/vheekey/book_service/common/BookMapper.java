package com.vheekey.book_service.common;

import com.vheekey.book_service.documents.Book;
import com.vheekey.book_service.enums.BookStatus;
import com.vheekey.book_service.requests.BookRequest;
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

    public void updateBook(Book book, BookRequest request) {
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setDescription(request.getDescription());
        book.setLocation(request.getLocation());
        book.setType(request.getBookType());
        book.setCategory(request.getCategory());
        book.setIsbn(request.getIsbn());
        book.setFine(BigDecimal.valueOf(request.getFine()));
    }
}
