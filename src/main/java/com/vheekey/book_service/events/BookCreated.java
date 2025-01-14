package com.vheekey.book_service.events;

import com.vheekey.book_service.enums.BookStatus;
import lombok.Data;

@Data
public class BookCreated {
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
            BookStatus status
    ) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.status = status;
    }
}
