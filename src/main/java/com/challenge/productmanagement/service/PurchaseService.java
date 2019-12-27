package com.challenge.productmanagement.service;

import com.challenge.productmanagement.dto.PurchaseDTO;
import com.challenge.productmanagement.model.Purchase;

import java.util.Calendar;

public interface PurchaseService {
    Iterable<Purchase> findAll();

    Iterable<Purchase> findAllBetweenDates(Calendar startDate, Calendar endDate);

    Purchase create(PurchaseDTO purchaseDTO);
}
