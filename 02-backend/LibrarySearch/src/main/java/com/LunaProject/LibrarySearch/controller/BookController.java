package com.LunaProject.LibrarySearch.controller;

import com.LunaProject.LibrarySearch.entity.Book;
import com.LunaProject.LibrarySearch.service.BookService;
import com.LunaProject.LibrarySearch.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("api/books")
public class BookController {
    private BookService bookService;
    @Autowired
    public BookController(BookService bookService){
        this.bookService=bookService;
    }
    @GetMapping("/secure/ischeckedout/byuser")
    public Boolean checkoutBookByUser(@RequestHeader(value = "Authorization")String token,
                                      @RequestParam Long bookId) throws Exception{
        String userEmail = ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        return bookService.checkoutBookByUser(userEmail,bookId);
    }

    //we are expecting something in the request header that has a key of authorization
    // pull the value our into the variable called token
    @GetMapping("/secure/currentloans/count")
    public int currentloansCount(@RequestHeader(value = "Authorization")String token){
        String userEmail = ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        return bookService.currentLoansCount(userEmail);
    }
    @PutMapping("/secure/checkout")
    public Book checkoutBook(@RequestHeader(value = "Authorization")String token,
                             @RequestParam Long bookId) throws Exception{
        String userEmail = ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        return bookService.checkoutBook(userEmail,bookId);
    }

}
