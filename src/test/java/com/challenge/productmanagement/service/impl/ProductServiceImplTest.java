package com.challenge.productmanagement.service.impl;

import com.challenge.productmanagement.Application;
import com.challenge.productmanagement.dto.ProductDTO;
import com.challenge.productmanagement.model.Product;
import com.challenge.productmanagement.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @Test
    void createNewProduct() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Orange");
        productDTO.setPrice(BigDecimal.valueOf(1.8));

        Product result = productService.create(productDTO);

        assertNotNull(result.getId());
        assertEquals("Orange", result.getName());
        assertEquals(BigDecimal.valueOf(1.8), result.getPrice());

        productService.delete(result.getId());
    }

    @Test
    void updateExistingProduct() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("Apple");
        productDTO.setPrice(BigDecimal.valueOf(1.1));

        Product apple = productService.create(productDTO);
        int productsSaved = ((Collection<?>) productService.findAll()).size();
        productDTO.setPrice(BigDecimal.valueOf(2.3));

        Optional<Product> result = productService.save(productDTO, apple.getId());
        assertTrue(result.isPresent());
        assertNotNull(result.get().getId());
        assertEquals("Apple", result.get().getName());
        assertEquals(BigDecimal.valueOf(2.3), result.get().getPrice());
        assertEquals(productsSaved, ((Collection<?>) productService.findAll()).size());

        productService.delete(result.get().getId());
    }

    @Test
    void updateInexistingProduct() {
        Optional<Product> result = productService.save(new ProductDTO(), 0L);
        assertFalse(result.isPresent());
    }

    @Test
    void findAllProducts() {
        int productsSaved = ((Collection<?>) productService.findAll()).size();

        Product result = productService.create(new ProductDTO());

        assertNotNull(productService.findAll());
        assertEquals(productsSaved + 1, ((Collection<?>) productService.findAll()).size());

        productService.delete(result.getId());
    }
}