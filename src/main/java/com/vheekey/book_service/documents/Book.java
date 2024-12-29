package com.vheekey.book_service.documents;

import com.vheekey.book_service.enums.BookStatus;
import com.vheekey.book_service.enums.BookType;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "books")
@Data
public class Book {
    @Id
    private String id;

    @Indexed
    private String title;

    private String location; //file url or shelf location

    @Indexed
    private String author;

    private String category;

    private String description;

    private BookStatus status;

    private BookType type;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private BigDecimal fine;
}

