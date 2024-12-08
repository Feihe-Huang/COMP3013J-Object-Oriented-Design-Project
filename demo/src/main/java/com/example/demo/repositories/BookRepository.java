package com.example.demo.repositories;

/**
 * This is an interface for book entities querying.
 * Auto query powered by the JPA and SpringBoots.
 */

import com.example.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    Book findBookById(Long id);
    List<Book> findAllByTitle(String title);
    Book findBookByIsbnNumber(String isbn);
    Book save(Book book);
}
