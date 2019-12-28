package com.challenge.productmanagement.service.impl;

import com.challenge.productmanagement.Application;
import com.challenge.productmanagement.dto.ProductDTO;
import com.challenge.productmanagement.dto.PurchaseDTO;
import com.challenge.productmanagement.model.Product;
import com.challenge.productmanagement.model.Purchase;
import com.challenge.productmanagement.service.ProductService;
import com.challenge.productmanagement.service.PurchaseService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
class PurchaseServiceImplTest {

    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private ProductService productService;

    @Test
    void findAllBetweenDates() {
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.DAY_OF_MONTH, -1);
        int ordersPlaced = ((Collection<?>) purchaseService.findAllBetweenDates(startDate, Calendar.getInstance())).size();

        PurchaseDTO purchaseDTO = new PurchaseDTO();
        purchaseDTO.setBuyerEmail("abc@gmail.com");
        purchaseDTO.setProductsIds(new ArrayList<>());

        Purchase result = purchaseService.create(purchaseDTO);

        assertEquals(ordersPlaced + 1, ((Collection<?>) purchaseService.findAllBetweenDates(startDate, Calendar.getInstance())).size());

        purchaseService.delete(result.getId());
    }

    @Test
    void placeAnOrder() {
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        purchaseDTO.setBuyerEmail("abc@gmail.com");
        purchaseDTO.setProductsIds(new ArrayList<>());

        Purchase result = purchaseService.create(purchaseDTO);
        assertNotNull(result.getId());
        assertEquals("abc@gmail.com", result.getBuyerEmail());
        assertNotNull(result.getProducts());
        assertEquals(0, result.getProducts().size());
        assertEquals(BigDecimal.ZERO, result.getTotalValue());
        assertNotNull(result.getPurchaseTime());

        purchaseService.delete(result.getId());
    }

    @Test
    void placeAnOrderWithExistingProduct() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Orange");
        productDTO.setPrice(BigDecimal.valueOf(1.51));

        Product orange = productService.create(productDTO);

        PurchaseDTO purchaseDTO = new PurchaseDTO();
        purchaseDTO.setBuyerEmail("abc@gmail.com");
        purchaseDTO.setProductsIds(new ArrayList<>(List.of(orange.getId())));

        Purchase result = purchaseService.create(purchaseDTO);
        assertNotNull(result.getId());
        assertEquals(1, result.getProducts().size());
        assertEquals(BigDecimal.valueOf(1.51), result.getTotalValue());

        purchaseService.delete(result.getId());
        productService.delete(orange.getId());
    }

    @Test
    void changeProductPriceWithoutChangingOrderValue() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Orange");
        productDTO.setPrice(BigDecimal.valueOf(1.91));

        Product orange = productService.create(productDTO);

        PurchaseDTO purchaseDTO = new PurchaseDTO();
        purchaseDTO.setBuyerEmail("abc@gmail.com");
        purchaseDTO.setProductsIds(new ArrayList<>(List.of(orange.getId())));

        Purchase result = purchaseService.create(purchaseDTO);
        assertEquals(BigDecimal.valueOf(1.91), result.getTotalValue());

        productDTO.setPrice(BigDecimal.ONE);
        productService.save(productDTO, orange.getId());

        assertEquals(BigDecimal.valueOf(1.91), result.getTotalValue());

        purchaseService.delete(result.getId());
        productService.delete(orange.getId());
    }
}