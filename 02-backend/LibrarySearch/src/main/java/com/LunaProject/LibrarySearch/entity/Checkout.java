package com.LunaProject.LibrarySearch.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "checkout")
@Data
public class Checkout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id;
    @Column(name = "user_email")
    private String userEmail;
    @Column(name = "checkout_date")
    private String CheckoutDate;
    @Column(name = "return_date")
    private String ReturnDate;
    @Column(name = "book_id")
    private Long bookId;
    public Checkout(){ }
    public Checkout(String userEmail, String CheckoutDate, String ReturnDate, Long bookId){
        this.userEmail=userEmail;
        this.CheckoutDate = CheckoutDate;
        this.ReturnDate=ReturnDate;
        this.bookId=bookId;
    }
}
