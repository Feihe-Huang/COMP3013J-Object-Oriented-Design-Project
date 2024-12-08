package com.example.demo.model;


/**
 * This class represents a real customer in the library
 * This class includes customer's basic information (e.g. address or dob)
 * Relationship like one to many is for the rental record.
 */

import org.hibernate.annotations.Proxy;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
@Proxy(lazy = false)
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String address;
    private LocalDate dateOfBirth;
    private Integer maxRentals;
    private Integer lateFees;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<BookRental> rentRecords;


    public Customer(String name, String email, String address, LocalDate dateOfBirth, Integer maxRentals) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.maxRentals = maxRentals;
        this.lateFees = 0;
    }

    public Customer(){

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Integer getMaxRentals() {
        return maxRentals;
    }

    public void addRentRecord(BookRental bookRental){
        this.rentRecords.add(bookRental);
    }

    public List<BookRental> getRentalRecord() {
        return rentRecords;
    }

    public Integer getLateFees() {
        return lateFees;
    }

    public void setLateFees(Integer lateFees) {
        this.lateFees = lateFees;
    }
}
