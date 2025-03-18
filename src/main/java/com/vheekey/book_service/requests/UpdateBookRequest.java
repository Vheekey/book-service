package com.vheekey.book_service.requests;

import com.mongodb.lang.Nullable;
import com.vheekey.book_service.documents.Book;
import com.vheekey.book_service.enums.BookType;
import com.vheekey.book_service.enums.Category;
import com.vheekey.book_service.requests.validators.UniqueValue;
import com.vheekey.book_service.requests.validators.ValidBookType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateBookRequest {
    private String title;

    private String author;

    private String location;

    private Category category;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;

    @Min(value = 0, message = "Fine amount is required and not less than 0")
    private Double fine;

    @Nullable
    @ValidBookType
    private BookType bookType;

    @UniqueValue(field = "isbn", message = "ISBN already exists", collection = Book.class)
    private String isbn;
}
