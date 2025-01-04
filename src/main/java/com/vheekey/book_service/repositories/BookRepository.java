package com.vheekey.book_service.repositories;

import com.vheekey.book_service.documents.Book;
import com.vheekey.book_service.enums.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    Optional<Book> findByAuthor(String author);

    Optional<Book> findByTitle(String title);

    Page<Book> findByCategory(Category category, Pageable pageable);

    @Query("{ $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'author': { $regex: ?0, $options: 'i' } } ] }")
    Page<Book> searchBooks(String keyword, Pageable pageable);
}
