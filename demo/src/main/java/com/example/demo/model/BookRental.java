package com.example.demo.model;
import org.hibernate.annotations.Proxy;
import javax.persistence.*;
import java.time.LocalDate;

/**
 * This represent a single rent record in the library: A user rent A single copy.
 * So, relationships includes Many to many to the book copy and Many to one for the book and also
 * Many to one to the customer. At the sametime this class will also remember latefees or status or dates relevant to
 * the rant process.
 */

@Proxy(lazy = false)
@Entity
public class BookRental {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDate checkOutDate;
    private LocalDate expectedReturnDate;
    private Integer lateFees;
    private LocalDate actualReturnDate;
    private RentalStatus status;

    @ManyToOne
    @JoinColumn(name = "book_copy")
    private BookCopy bookCopy;

    @ManyToOne
    @JoinColumn(name = "book")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public Book getBook() {
        return new Book();
    }

    public BookCopy getBookCopy(){
        return bookCopy;
    }

    public BookRental(BookCopy bookCopy, Customer customer, LocalDate checkOutDate, LocalDate actualReturnDate) {
        this.checkOutDate = checkOutDate;
        this.expectedReturnDate = actualReturnDate;
        this.bookCopy = bookCopy;
        this.customer = customer;
        this.status = RentalStatus.RENTED;
        this.lateFees = 0;
    }

    public BookRental(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RentalStatus getStatus() {
        return status;
    }

    public void setStatus(RentalStatus status) {
        this.status = status;
    }

    public void setActualReturnDate(LocalDate actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }

    public Integer getLateFees() {
        return lateFees;
    }

    public LocalDate getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(LocalDate expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public LocalDate getActualReturnDate() {
        return actualReturnDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }
}
