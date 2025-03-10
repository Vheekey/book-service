package com.vheekey.book_service.services.interfaces;

import com.vheekey.book_service.requests.BookRequest;
import com.vheekey.book_service.responses.BookResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookResponse addBook(@Valid BookRequest request);
    Page<BookResponse> searchBookWildCard(@Valid String keyword, Pageable pageable);
}
