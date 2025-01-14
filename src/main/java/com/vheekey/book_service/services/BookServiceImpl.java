package com.vheekey.book_service.services;

import com.vheekey.book_service.common.BookMapper;
import com.vheekey.book_service.documents.Book;
import com.vheekey.book_service.repositories.BookRepository;
import com.vheekey.book_service.requests.BookRequest;
import com.vheekey.book_service.responses.BookResponse;
import com.vheekey.book_service.services.interfaces.BookService;
import com.vheekey.book_service.services.interfaces.KafkaService;
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
}
