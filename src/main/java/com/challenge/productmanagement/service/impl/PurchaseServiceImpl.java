package com.challenge.productmanagement.service.impl;

import com.challenge.productmanagement.dto.PurchaseDTO;
import com.challenge.productmanagement.model.Product;
import com.challenge.productmanagement.model.Purchase;
import com.challenge.productmanagement.repository.PurchaseRepository;
import com.challenge.productmanagement.service.ProductService;
import com.challenge.productmanagement.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private ProductService productService;

    @Override
    public Iterable<Purchase> findAll() {
        return purchaseRepository.findAll();
    }

    @Override
    public Iterable<Purchase> findAllBetweenDates(Calendar startDate, Calendar endDate) {
        return purchaseRepository.findAllBetweenDates(startDate, endDate);
    }

    @Override
    public Purchase create(PurchaseDTO purchaseDTO) {
        Purchase purchase = new Purchase();
        List<Product> productsPurchased = new ArrayList<>();
        for (Long id : purchaseDTO.getProductsIds()) {
            productService.findById(id).ifPresent(product -> productsPurchased.add(product));
        }
        purchase.setProducts(productsPurchased);
        purchase.setTotalValue(
                purchase.getProducts().stream()
                        .map(Product::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add));
        purchase.setBuyerEmail(purchaseDTO.getBuyerEmail());
        purchase.setPurchaseTime(Calendar.getInstance());

        return purchaseRepository.save(purchase);
    }

    @Override
    public void delete(Long id) {
        purchaseRepository.deleteById(id);
    }
}
