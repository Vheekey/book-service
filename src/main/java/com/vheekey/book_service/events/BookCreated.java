package com.vheekey.book_service.events;

import com.vheekey.book_service.enums.BookStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookCreated {
    private final LocalDateTime createdAt;
    private String bookId;
    private String title;
    private String author;
    private String isbn;
    private BookStatus status;

    public BookCreated(
            String bookId,
            String title,
            String author,
            String isbn,
            BookStatus status,
            LocalDateTime createdAt
    ) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.status = status;
        this.createdAt = createdAt;
    }
}
