package com.challenge.productmanagement.controller;

import com.challenge.productmanagement.model.Purchase;
import com.challenge.productmanagement.service.PurchaseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

@RestController
@Validated
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @ApiOperation(value = "Visualize all orders.")
    @GetMapping("/orderss")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Purchase> findAll() {
        return purchaseService.findAll();
    }

    @ApiOperation(value = "Visualize all orders between dates, date format = 'yyyy.MM.dd HH:mm:ss'")
    @GetMapping("/orders")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Purchase> findAllBetweenDates(@RequestParam @DateTimeFormat(pattern = "yyyy.MM.dd HH:mm:ss") Calendar startDate,
                                                  @RequestParam @DateTimeFormat(pattern = "yyyy.MM.dd HH:mm:ss") Calendar endDate) {
        return purchaseService.findAllBetweenDates(startDate, endDate);
    }

    @ApiOperation(value = "Create a new order.")
    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public Purchase createOrder() {
        return purchaseService.create();
    }
}
