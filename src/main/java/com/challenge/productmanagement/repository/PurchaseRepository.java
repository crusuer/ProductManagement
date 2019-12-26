package com.challenge.productmanagement.repository;

import com.challenge.productmanagement.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Calendar;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    @Query("SELECT p FROM Purchase p WHERE p.purchaseTime BETWEEN :startDate AND :endDate")
    Iterable<Purchase> findAllBetweenDates(@Param("startDate") Calendar startDate, @Param("endDate") Calendar endDate);
}
