package com.vheekey.book_service.requests;

import com.vheekey.book_service.documents.Book;
import com.vheekey.book_service.enums.BookType;
import com.vheekey.book_service.enums.Category;
import com.vheekey.book_service.requests.validators.UniqueValue;
import com.vheekey.book_service.requests.validators.ValidBookType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookRequest {
    @NotNull(message = "Title is required")
    private String title;

    @NotNull(message = "Author is required")
    private String author;

    @NotNull(message = "Location is required (File url or shelf number)")
    private String location;

    @NotNull(message = "Category is required")
    private Category category;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    @Min(value = 0, message = "Fine amount is required and not less than 0")
    private double fine;

    @ValidBookType
    private BookType bookType;

    @NotNull(message = "ISBN is required")
    @UniqueValue(field = "isbn", message = "ISBN already exists", collection = Book.class)
    private String isbn;
}
