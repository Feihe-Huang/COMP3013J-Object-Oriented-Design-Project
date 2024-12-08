package com.example.demo.repositories;

/**
 * This is an interface for author entities querying.
 * Auto query powered by the JPA and SpringBoots.
 */

import com.example.demo.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Long> {
    Author findAuthorByName(String name);
    Author save(Author author);
}
