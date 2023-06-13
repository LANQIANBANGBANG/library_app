package com.LunaProject.LibrarySearch.dao;

import com.LunaProject.LibrarySearch.entity.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheckoutRepository extends JpaRepository<Checkout,Long> {
    Checkout findByUserEmailAndBookId (String userEmail, Long BookId);
    List<Checkout> findByUserEmail(String userEmail);
}
