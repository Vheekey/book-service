package com.vheekey.book_service.responses;

import com.vheekey.book_service.enums.BookStatus;
import com.vheekey.book_service.enums.BookType;
import com.vheekey.book_service.enums.Category;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class BookResponse {
    private String id;
    private String title;
    private String author;
    private String description;
    private Category category;
    private String location;
    private String isbn;
    private BookStatus status;
    private BookType type;
    private BigDecimal fine;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
