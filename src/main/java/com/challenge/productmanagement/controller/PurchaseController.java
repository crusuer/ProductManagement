package com.challenge.productmanagement.controller;

import com.challenge.productmanagement.dto.PurchaseDTO;
import com.challenge.productmanagement.model.Purchase;
import com.challenge.productmanagement.service.PurchaseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;

@RestController
@Validated
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @ApiOperation(value = "Retrieving all orders within a given time period, date format = 'yyyy-MM-ddTHH:mm:ss'")
    @GetMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = Iterable.class)
    })
    public ResponseEntity<Iterable<Purchase>> findAllBetweenDates(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Calendar startDate,
                                                                  @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Calendar endDate) {
        return ResponseEntity.ok(purchaseService.findAllBetweenDates(startDate, endDate));
    }

    @ApiOperation(value = "Placing an order")
    @PostMapping(value = "/orders", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Order placed", response = Purchase.class)
    })
    public ResponseEntity<Purchase> placeOrder(@Valid @RequestBody PurchaseDTO purchaseDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseService.create(purchaseDTO));
    }
}
