package com.LunaProject.LibrarySearch.service;

import com.LunaProject.LibrarySearch.dao.BookRepository;
import com.LunaProject.LibrarySearch.dao.CheckoutRepository;
import com.LunaProject.LibrarySearch.entity.Book;
import com.LunaProject.LibrarySearch.entity.Checkout;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
public class BookService {
    // this book checkout service involves two repositories
    private BookRepository bookRepository;
    private CheckoutRepository checkoutRepository;
    public BookService(BookRepository bookRepository, CheckoutRepository checkoutRepository){
        this.bookRepository=bookRepository;
        this.checkoutRepository=checkoutRepository;
    }
    public Book checkoutBook(String userEmail, Long bookId) throws Exception{
        Optional<Book> book = bookRepository.findById(bookId);
        Checkout validateCheckout = checkoutRepository.findByUserEmailAndBookId(userEmail,bookId);

        if(!book.isPresent()||validateCheckout!=null||book.get().getCopiesAvailable()<=0){
            throw new Exception("Book doesn't exist or already checked out by user");
        }

        book.get().setCopiesAvailable(book.get().getCopiesAvailable()-1);
        bookRepository.save(book.get());

        Checkout checkout = new Checkout(
                userEmail,
                LocalDate.now().toString(),
                LocalDate.now().plusDays(7).toString(),
                book.get().getId()
        );

        checkoutRepository.save(checkout);
        return book.get();
    }

    public boolean checkoutBookByUser(String userEmail, Long bookId) throws Exception{
        Checkout validateCheckout = checkoutRepository.findByUserEmailAndBookId(userEmail,bookId);
        if(validateCheckout!=null){
            return true;
        }else{
            return false;
        }
    }
    public int currentLoansCount(String userEmail){
        return checkoutRepository.findByUserEmail(userEmail).size();
    }
}
