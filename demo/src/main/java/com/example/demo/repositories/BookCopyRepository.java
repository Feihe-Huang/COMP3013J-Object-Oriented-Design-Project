package com.example.demo.repositories;

/**
 * This is an interface for book copy entities querying.
 * Auto query powered by the JPA and SpringBoots.
 */

import com.example.demo.model.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookCopyRepository extends JpaRepository<BookCopy,Long> {
    @Override
    Optional<BookCopy> findById(Long id);

}
