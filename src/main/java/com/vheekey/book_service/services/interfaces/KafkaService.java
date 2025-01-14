package com.vheekey.book_service.services.interfaces;

import com.vheekey.book_service.documents.Book;

public interface KafkaService {
    boolean send(Book book);
}
