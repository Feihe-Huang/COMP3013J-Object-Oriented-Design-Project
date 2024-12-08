package com.example.demo.model;

/**
 * This will represent a book copy in the library.
 * There are multiple book copies belong to a single type of book
 * Relationship like Many to one is for the book
 */

import org.hibernate.annotations.Proxy;

import javax.persistence.*;

import java.time.LocalDate;


@Proxy(lazy = false)
@Entity
public class BookCopy {
    @Id
    @GeneratedValue
    private Long id;
    private Boolean available;
    private LocalDate expectedReturnedDate;

    @ManyToOne
    @JoinColumn(name = "bookCopies")
    private Book book;

    public BookCopy() {

    }

    public BookCopy(Book book){
        this.book = book;
        this.available = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public void setExpectedReturnedDate(LocalDate expectedReturnedDate) {
        this.expectedReturnedDate = expectedReturnedDate;
    }

    public Book getBook(){
        return this.book;
    }

    public Boolean getAvailable() {
        return available;
    }

    public LocalDate getExpectedReturnedDate() {
        return expectedReturnedDate;
    }


}
