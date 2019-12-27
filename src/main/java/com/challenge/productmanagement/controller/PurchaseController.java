package com.challenge.productmanagement.controller;

import com.challenge.productmanagement.dto.PurchaseDTO;
import com.challenge.productmanagement.model.Purchase;
import com.challenge.productmanagement.service.PurchaseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;

@RestController
@Validated
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @ApiOperation(value = "Visualize all orders between dates, date format = 'yyyy-MM-dd'T'HH:mm:ss'")
    @GetMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Purchase> findAllBetweenDates(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Calendar startDate,
                                                  @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Calendar endDate) {
        return purchaseService.findAllBetweenDates(startDate, endDate);
    }

    @ApiOperation(value = "Create a new order.")
    @PostMapping(value = "/orders", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Purchase placeOrder(@Valid @RequestBody PurchaseDTO purchaseDTO) {
        return purchaseService.create(purchaseDTO);
    }
}
