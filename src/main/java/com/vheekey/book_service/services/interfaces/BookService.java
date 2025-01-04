package com.vheekey.book_service.services.interfaces;

import com.vheekey.book_service.requests.BookRequest;
import com.vheekey.book_service.responses.BookResponse;
import jakarta.validation.Valid;

public interface BookService {
    BookResponse addBook(@Valid BookRequest request);
}
