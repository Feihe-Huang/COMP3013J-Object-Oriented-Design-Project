package com.example.demo.model;

/**
 * This is an entity class for the author
 * Each class represent a real author in reality
 * This will store Many to Many relationship with the book class.
 * Meanwhile this will store the author's information
 * @author LouYiming
 */

import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.List;

@Proxy(lazy = false)
@Entity
public class Author {
    private String name;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

    @Id
    @GeneratedValue
    private Long id;

    public Author() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Author(String name) {
        this.name = name;
    }


}
