package com.LunaProject.LibrarySearch.dao;

import com.LunaProject.LibrarySearch.entity.Review;
import jakarta.persistence.Id;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findByBookId(@RequestParam("book_id") Long bookId, Pageable pageable);
    // When you define a method in your repository interface
    // using the naming convention of findBy<AttributeName>,
    // Spring Data JPA analyzes the method name and generates a query based on it.

    //The findByBookId method expects a parameter named bookId of type Long.
    // This parameter is used to pass the value you want to use for filtering.
}
