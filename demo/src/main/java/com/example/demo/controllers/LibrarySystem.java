package com.example.demo.controllers;

import com.example.demo.model.*;
import com.example.demo.services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * This is the controller.
 * The main role is to respond further to user input.
 * It also processes the model in specific ways,
 * such as adding or deleting or finding.
 */

@Controller
public class LibrarySystem {
    private final LibraryService libraryService;

    @Autowired
    public LibrarySystem(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/")
    public String indexPage(){
        return "index";
    }

    @RequestMapping("/addRental")
    public String addRental(@RequestParam(name="customerID") String customerID,
                            @RequestParam(name="bookCopyID") String bookCopyID,
                            @RequestParam(name="checkOutDate") String checkOutDate,
                            @RequestParam(name="returnDate") String returnDate, Model model){
        Customer customer =  libraryService.addRental(Long.parseLong(customerID),Long.parseLong(bookCopyID),LocalDate.parse(checkOutDate),LocalDate.parse(returnDate));
//        List<BookRental> rentalRecords = customer.getRentalRecord(); // not work because there are no any interaction between rental and customer
        List<BookRental> rentalRecords  = libraryService.searchAllRental(customer);
        model.addAttribute("customer",customer);
        model.addAttribute("rentRecords",rentalRecords);
        return "addRental";
    }

    @GetMapping("/addRentalPage")
    public String addRentalPage(){
        return "addRental";
    }

    @RequestMapping("/returnBook")
    public String returnBook(@RequestParam(name="customerID") String customerID,
                             @RequestParam(name="bookCopyID") String bookCopyID,
                             @RequestParam(name="returnDate") String returnDate, Model model){
        //return the book of a user
        Customer customer = libraryService.returnBook(Long.parseLong(customerID),Long.parseLong(bookCopyID),LocalDate.parse(returnDate));
        List<BookRental> rentalRecords = customer.getRentalRecord();
        //return rent records
        model.addAttribute("customer",customer);
        model.addAttribute("rentRecords",rentalRecords);
        return "returnBook";
    }

    @GetMapping("/returnPage")
    public String returnBookPage(){
        return "returnBook";
    }


    @RequestMapping("/addAuthor")
    public String addAuthor(@RequestParam(name="authorName") String name,
                            Model model){
        libraryService.getAuthor(name);
        return "redirect:/";
    }

    @RequestMapping("/addAuthorPage")
    public String addAuthor(){
        return "addAuthor";
    }

    @RequestMapping("/addBook")
    public String addBook(
            @RequestParam(name="title") String title,
            @RequestParam(name="authors") String authors,
            @RequestParam(name="ISBN") String ISBN,
            @RequestParam(name="section") String section,
            @RequestParam(name="shelf") String shelf,
            Model model) {
        //invoke the get book method from the service
        libraryService.getBook(title, authors, ISBN, section, shelf);
        System.out.println(title + authors + ISBN + section + shelf);
        return "redirect:/";
    }

    @GetMapping("/addBookPage")
    public String addBook(){
        return "addBook";
    }

    @GetMapping("/123")
    public String stubTest(){
        return "sb";
    }

    @GetMapping("/addCopyPage")
    public String addCopyPage(){
        return "addCopy";
    }

    @PostMapping("/addCopy")
    public String addBookCopies(
            @RequestParam(name="bookId") String bookId,
            @RequestParam(name="numberCopies") String numberCopies,
            Model model) {
        //show a idList of one account
        List<Integer> idList = libraryService.addBookCopies(Long.parseLong(bookId), Integer.parseInt(numberCopies));
        Book book = libraryService.getBook(Long.valueOf(bookId));
        model.addAttribute("book", book);
        model.addAttribute("idList", idList);
        return "addCopy";
    }

    @PostMapping("/removeCopy")
    public String removeBookCopy(
            @RequestParam(name="bookCopyId") String bookCopyId,
            Model model) {
        libraryService.removeBookCopy(Long.valueOf(bookCopyId));
        return "redirect:/";
    }

    @GetMapping("/removeCopyPage")
    public String removeCopy(){
        return "removeCopy";
    }

    @GetMapping("/addCustomerPage")
    public String addCustomerHtml(){
        return "addCustomer";
    }

    @PostMapping("/addCustomer")
    public String addCustomer(
            @RequestParam(name="customerName") String customerName,
            @RequestParam(name="customerDateOfBirth") String customerDateOfBirth,
            @RequestParam(name = "customerEmail") String customerEmail,
            @RequestParam(name = "customerAddress") String customerAddress,
            @RequestParam(name = "customerMaxRentals") String customerMaxRentals,
            Model model) {
        LocalDate dateOfBirth = LocalDate.parse(customerDateOfBirth);
        int maxRentals = Integer.parseInt(customerMaxRentals);

        Customer c = libraryService.getCustomer(customerName, dateOfBirth, customerEmail, customerAddress, maxRentals);
        List<BookRental> rr = c.getRentalRecord();
        //Return the model value
        model.addAttribute("customerName", customerName);
        model.addAttribute("thisCustomer", c);
        model.addAttribute("RentalRecord", rr);

        return "addCustomer";
    }

    @PostMapping("/findBook")
    public String findBook(
            @RequestParam(name="text") String text,
            Model model) {
        List<Book> bookList = libraryService.findBook(text);
        System.out.println(bookList);

        model.addAttribute("bookList", bookList);
        return "findBook";
    }

    @RequestMapping("/findBookPage")
    public String findBookPage(){
        return "findBook";
    }

    @GetMapping("/customerDetails")
    public String customerDetails(){
        return "customerDetails";
    }
    @PostMapping("/customerDetailsForm")
    public String findCustomer(
            @RequestParam(name="text") String text,
            Model model){
        //Find from the service
        List<Customer> customerList = libraryService.findCustomer(text);
        model.addAttribute("name", "smoker");
        model.addAttribute("customerList", customerList);

        return "customerDetails";
    }
    @RequestMapping("/customerDetails/{c_id}")
    public String showCustomer(
            @PathVariable("c_id") String customerId,
            Model newModel){
        Long id = Long.valueOf(customerId);
        Customer c = libraryService.getCustomer(id);
        List<BookRental> rr = libraryService.searchAllRental(c);//Search the records
        //show details and add them to a list
        newModel.addAttribute("single", "smoker");
        newModel.addAttribute("thisCustomer", c);
        newModel.addAttribute("RentalRecord", rr);

        return "customerDetails";
    }

    @GetMapping("/recordPaymentPage")
    public String addPaymentPage(){
        return "recordPayment";
    }

    @PostMapping("/recordPayment")
    public String addPayment(
            @RequestParam(name = "customerId") String customerId,
            @RequestParam(name = "payment") String payment,
            Model model
    ){
        Long id = Long.valueOf(customerId);
        int paymentAccount = Integer.parseInt(payment);
        //get the payment
        Customer customer =  libraryService.addPayment(id, paymentAccount);

        if (customer != null){
            // get rental records
            List<BookRental> rentalRecord = libraryService.searchAllRental(customer);
            model.addAttribute("name", "smoker");
            model.addAttribute("thisCustomer", customer);
            model.addAttribute("rentalRecord", rentalRecord);
        }
        return "recordPayment";
    }

    // 11 - waiting list /
// start the page from "/findBookWaitingListPage"
    @RequestMapping("/findBookWaitingListPage")
    public String findBookWaitingListPage(){
        return "addWaiting";
    }
    @PostMapping("/findBookWaitingList")
    public String findBookWaitingList(
            @RequestParam(name="text") String text,
            Model model) {
        List<Book> bookList = libraryService.findBook(text);
        model.addAttribute("bookList", bookList);
        return "addWaiting";
    }

    // Select the specific book and show the book and copies info
    @RequestMapping("/findBookWaitingList/{b_id}")
    public String selectBook(
            @PathVariable("b_id") String book_id,
            Model newModel){
        Long id = Long.valueOf(book_id);

        // get the book and copies
        Book b = libraryService.getBook(id);
        List<BookCopy> bc = b.getCopies();

        newModel.addAttribute("specific", "smoker");
        newModel.addAttribute("thisBook", b);
        newModel.addAttribute("bookCopies", bc);

        return "addWaiting";
    }

    // Go to a new page to enter customer ID

    // Go to a new page to enter customer ID
    @RequestMapping("/waitingList/{b_id}")
    public String enterCustomerID(
            @PathVariable("b_id") String book_id,
            @RequestParam(name = "customer_id", required = false) String customer_id,
            Model model
    ){
        // pass the book_id to the page
        model.addAttribute("book_id", book_id);

        if (customer_id != null){
            Long bookID = Long.valueOf(book_id);
            Long customerID = Long.valueOf(customer_id);

            // pass the IDs to the service
            Book b = libraryService.addToWaitingList(bookID, customerID);
            List<BookCopy> bc = b.getCopies();
            List<Customer> waitingList = b.getWaitingList();

            model.addAttribute("Result", "yes");
            model.addAttribute("thisBook", b);
            model.addAttribute("waitingList", waitingList);
        }

        return "waitingTwo";
    }




}
