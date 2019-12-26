package com.challenge.productmanagement.service.impl;

import com.challenge.productmanagement.model.Purchase;
import com.challenge.productmanagement.repository.PurchaseRepository;
import com.challenge.productmanagement.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    public Iterable<Purchase> findAll() {
        return purchaseRepository.findAll();
    }

    @Override
    public Iterable<Purchase> findAllBetweenDates(Calendar startDate, Calendar endDate) {
        return purchaseRepository.findAllBetweenDates(startDate, endDate);
    }

    @Override
    public Purchase create() {
        Purchase purchase = new Purchase();
        purchase.setBuyerEmail("XPTO");
        purchase.setPurchaseTime(Calendar.getInstance());

        return purchaseRepository.save(purchase);
    }
}
