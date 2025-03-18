package com.vheekey.book_service.services;

import com.vheekey.book_service.common.BookMapper;
import com.vheekey.book_service.documents.Book;
import com.vheekey.book_service.repositories.BookRepository;
import com.vheekey.book_service.requests.BookRequest;
import com.vheekey.book_service.requests.UpdateBookRequest;
import com.vheekey.book_service.responses.BookResponse;
import com.vheekey.book_service.services.interfaces.BookService;
import com.vheekey.book_service.services.interfaces.KafkaService;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper;
    private final BookRepository bookRepository;
    private final KafkaService kafkaService;

    public BookServiceImpl(
            BookMapper bookMapper,
            BookRepository bookRepository,
            KafkaService kafkaService
    ) {
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
        this.kafkaService = kafkaService;
    }

    @Override
    public BookResponse addBook(BookRequest request) {
        Book book = this.bookMapper.toEntity(request);
        book = this.bookRepository.save(book);
        this.kafkaService.send(book);

        return this.bookMapper.toResponse(book);
    }

    @Override
    public Page<BookResponse> searchBookWildCard(String keyword, Pageable pageable) {
        Page<Book> books = bookRepository.searchBooks(keyword, pageable);
        return books.map(bookMapper::toResponse);
    }

    @Override
    public BookResponse updateBook(String id, UpdateBookRequest request) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        book = bookMapper.updateBook(book, request);
        Book updatedBook = bookRepository.save(book);

        return bookMapper.toResponse(updatedBook);
    }
}
