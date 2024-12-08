package com.example.demo.model;

/**
 * This Book class will represent a kind of Books in the library.
 * So, this class will maintain multiple book copies which represent a real book in the library.
 * Relationship like Many to Many with authors
 * And One to many to the copies and waiting customers.
 */

import org.hibernate.annotations.Proxy;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Proxy(lazy = false)
@Entity
public class Book {

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Author> authors;

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private List<BookCopy> bookCopies;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Customer> waitingList;

    private String title;
    private String isbnNumber;
    private String section;
    private String shelf;

    @Id
    @GeneratedValue
    private Long id;

    public Book(String title, String isbnNumber, ArrayList<Author> authors, String section, String shelf){
        this.title = title;
        this.isbnNumber = isbnNumber;
        this.section = section;
        this.shelf = shelf;
        this.authors = authors;
        this.waitingList = new ArrayList<>();
    }

    public Book() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle(){
        return this.title;
    }

    public void addCopy(BookCopy bookCopy){
        this.bookCopies.add(bookCopy);
    }

    public List<BookCopy> getCopies(){
        return bookCopies;
    }

    public void addWaitingList(Customer customer){
        waitingList.add(customer);
    }

    public void removeCopy(BookCopy bookCopy){
        this.bookCopies.remove(bookCopy);
    }

    public String getIsbnNumber() {
        return isbnNumber;
    }

    public String getSection() {
        return section;
    }

    public String getShelf() {
        return shelf;
    }

    public List<BookCopy> getBookCopies() {
        return bookCopies;
    }

    public List<Customer> getWaitingList() {
        return waitingList;
    }
}
