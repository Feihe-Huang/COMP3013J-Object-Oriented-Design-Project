package com.example.demo.services;

import com.example.demo.model.*;
import com.example.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * This a way to perform operations using the models.
 * Encapsulates generic business logic, operations.
 * Interaction with the data including book, author,
 * book copy, book rental, customer, rental status.
 */

@Service
public class LibraryService {
    private final AuthorRepository authors;
    private final BookCopyRepository bookcopies;
    private final BookRentalRepository bookrentals;
    private final CustomerRepository customers;
    private final BookRepository books;

    @Autowired
    public LibraryService(AuthorRepository a,BookRepository b, BookRentalRepository br, CustomerRepository cr, BookCopyRepository bcr) {
        this.authors = a;
        this.bookcopies = bcr;
        this.bookrentals = br;
        this.customers = cr;
        this.books = b;
    }

    public Customer addRental(Long customerID, Long copyID, LocalDate checkOutDate, LocalDate returnDate){
        //Add a rental record
        Optional<Customer> customerOptional = customers.findById(customerID);
        Optional<BookCopy> bookCopyOptional = bookcopies.findById(copyID);
        BookCopy bookCopy =  bookCopyOptional.get();
        Customer customer = customerOptional.get();
        //Find the instances

        if(customer.getRentalRecord().size() >= customer.getMaxRentals()){//Reached at the maximum and stop this case.
            //In this case, nothing happens because the user could not rent any books
            return customer;
        }
        bookCopy.setAvailable(false);
        //else case, this copy will be rented
        bookCopy.setExpectedReturnedDate(returnDate);
        bookcopies.save(bookCopy);
        //update the book copy
        BookRental bookRental = new BookRental(bookCopy, customer, checkOutDate, returnDate);
        bookrentals.save(bookRental);
        return customer;
    }

    public List<BookRental> searchAllRental(Customer customer){
        return bookrentals.findAllByCustomer(customer);
    }

    public Customer returnBook(Long customerID, Long copyID,LocalDate returnDate){
        //Return a book
        Optional<Customer> customerOptional = customers.findById(customerID);
        Optional<BookCopy> bookCopyOptional = bookcopies.findById(copyID);
        BookCopy bookCopy =  bookCopyOptional.get();
        Customer customer = customerOptional.get();
        //Find instances

        List<BookRental> rentalRecords = bookrentals.findBookRentalsByCustomerAndBookCopy(customer,bookCopy);
        bookCopy.setExpectedReturnedDate(null);
        bookCopy.setAvailable(true);//Current are available to be rented
        bookcopies.save(bookCopy);
        for (BookRental bookrental:rentalRecords) {//Loop the rent records that returned
            if(bookrental.getStatus() == RentalStatus.RENTED){
                bookrental.setStatus(RentalStatus.RETURNED);
                bookrental.setActualReturnDate(returnDate);
                //Check if the return is expired
                if(bookrental.getActualReturnDate().compareTo(bookrental.getExpectedReturnDate())>0){//late fees
                    Integer originLateFees = customer.getLateFees();
                    //Get original late fees
                    customer.setLateFees(originLateFees + bookrental.getLateFees());//update late fees
                    customers.save(customer);
                    //Save customer
                }
                bookrentals.save(bookrental);
            }
        }
        return customer;
    }

    public Book addWaitingList(Long bookID, Long customerID){
        Book book = books.findBookById(bookID);
        Customer customer = null;
        Optional<Customer> customerOptional = customers.findById(customerID);
        if(customerOptional.isPresent()) {
            customer = customerOptional.get();
        }
        book.addWaitingList(customer);
        books.save(book);
        return book;
    }

    public Book getBook(Long id){
        return books.findBookById(id);
    }

    public Author getAuthor(String name){
        //Get an author and check
        Author author = authors.findAuthorByName(name);
        //The author do not exist and creat a new one
        if (author == null) {
            author = new Author(name);
            authors.save(author);
        }
        return author;
    }

    public Book getBook(String title, String authors, String ISBN, String section, String shelf){
        Book book = books.findBookByIsbnNumber(ISBN);
        //Query the book at first
        if (book == null) {
            //Book do not exist, create a new one.
            ArrayList<Author> authorList = new ArrayList<>();
            List<String> authorIn = List.of(authors.split(","));
            for (int i = 0; i < authorIn.size(); i++){
                Author au = getAuthor(authorIn.get(i));
                authorList.add(au);
                //Also add authors or other information
            }
            book = new Book(title, ISBN, authorList, section, shelf);
            books.save(book);
        }
        return book;
    }

    public List<Integer> addBookCopies(long bookId, int numberCopies){
        Book book = books.findBookById(bookId);
        List<Integer> idList = new ArrayList<>();
        for (int i = 0; i <= numberCopies; i++){
            BookCopy copy = new BookCopy(book);
            bookcopies.save(copy);
            //Creat copies for the book
            int id = Integer.parseInt(String.valueOf(copy.getId()));
            book.addCopy(copy);
            idList.add(id);
        }
        return idList;
    }

    public void removeBookCopy(Long bookCopyId){
        //Remove the book's copy
        Optional<BookCopy> bookCopy = bookcopies.findById(bookCopyId);
        Book book = bookCopy.get().getBook();
        book.removeCopy(bookCopy.get());
        bookcopies.delete(bookCopy.get());
        books.save(book);
    }

    public Customer getCustomer(String name, LocalDate dateOfBirth, String email, String address, Integer maxRentals) {
        Customer c = customers.findCustomerByEmail(email);
        //Query a customer
        if (c == null){
            //This customer does not exist and must creat a new one
            c = new Customer(name, email, address, dateOfBirth, maxRentals);
            customers.save(c);
        }
        return c;
    }

    public List<Book> findBook(String text){
        List<Book> bookList = new ArrayList<>();
        if (isPureNumber(text)){
            Long id = Long.parseLong(text);
            Book book = books.findBookById(id);
            //Find books by using their id.
            bookList.add(book);
        }
        Book book = books.findBookByIsbnNumber(text);
        if (book != null){
            //find the book by ISBN and add to the list
            bookList.add(book);
        }

        List<Book> bookList1 = books.findAllByTitle(text);
        if (bookList1 != null) {
            //add the books find by title
            bookList.addAll(bookList1);
        }

//        Author author = authors.findAuthorByName(text);
//        List<Book> booksAu = books.findByAuthor(author);
//        bookList.addAll(booksAu);

        return bookList;
    }

    public List<Customer> findCustomer(String text){
        List<Customer> customerList = new ArrayList<>();

        // if the text only contains numbers
        if (isPureNumber(text)){
            Long id = Long.valueOf(text);
            Optional<Customer> customerOptional = customers.findById(id);
            if (customerOptional.isPresent()){
                Customer c1 = customerOptional.get();
                customerList.add(c1);
            }
        }

        // find by email
        Customer c2 = customers.findCustomerByEmail(text);
        if (c2 != null){
            customerList.add(c2);
        }


        // find all by name
        List<Customer> nameMatched = customers.findCustomersByName(text);
        customerList.addAll(nameMatched);

        // find all by address
        List<Customer> addressMatched = customers.findCustomersByAddress(text);
        customerList.addAll(addressMatched);

        return customerList;
    }
    private boolean isPureNumber(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        //Check the input using regex
        return pattern.matcher(str).matches();
    }
    public Customer getCustomer(Long id){
        Optional<Customer> customerOptional = customers.findById(id);
        if (customerOptional.isPresent()){
            return customerOptional.get();
        } else{
            return null;
        }
    }

    public Customer addPayment(Long id, int paymentAccount){//This method could add a payment information to a customer
        //After the payment, the user's late fees will be updated in the database

        Customer customer = null;
        Optional<Customer> customerOptional = customers.findById(id);
        if (customerOptional.isPresent()){
            customer = customerOptional.get();
            int currentFees = customer.getLateFees();

            // Calculate the lateFees
            customer.setLateFees(currentFees - paymentAccount);
            customers.save(customer);
        }

        return customer;
    }

    public Book addToWaitingList(long bookID, long customerID){
        Optional<Customer> customerOptional = customers.findById(customerID);
        if(customerOptional.isPresent()){
            Customer cus = customerOptional.get();

            // get the book
            Book book = books.findBookById(bookID);
            book.addWaitingList(cus);
            books.save(book);

            return book;
        } else{
            return null;
        }
    }




}
