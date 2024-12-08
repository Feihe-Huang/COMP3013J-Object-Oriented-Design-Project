package com.example.demo.repositories;

/**
 * This is an interface for book rent record entities querying.
 * Auto query powered by the JPA and SpringBoots.
 */

import com.example.demo.model.BookCopy;
import com.example.demo.model.BookRental;
import com.example.demo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRentalRepository extends JpaRepository<BookRental, Long> {
    BookRental save(BookRental bookRental);
    List<BookRental> findAllByCustomer(Customer customer);
    List<BookRental> findBookRentalsByCustomerAndBookCopy(Customer customer, BookCopy bookCopy);
}
