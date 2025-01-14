package com.vheekey.book_service.services;

import com.vheekey.book_service.documents.Book;
import com.vheekey.book_service.events.BookCreated;
import com.vheekey.book_service.services.interfaces.KafkaService;
import org.springframework.stereotype.Service;

@Service
public class KafkaServiceImpl implements KafkaService {

    private final KafkaProducerService kafkaProducerService;

    public KafkaServiceImpl(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @Override
    public boolean send(Book book) {
        BookCreated bookCreated = new BookCreated(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getStatus()
        );

        return this.kafkaProducerService.publishMessage(bookCreated);
    }

}
