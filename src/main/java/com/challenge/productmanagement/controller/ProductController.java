package com.challenge.productmanagement.controller;

import com.challenge.productmanagement.dto.ProductDTO;
import com.challenge.productmanagement.model.Product;
import com.challenge.productmanagement.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Optional;

@RestController
@Validated
public class ProductController {
    @Autowired
    private ProductService productService;

    @ApiOperation(value = "Visualize all products.")
    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Product> findAll() {
        return productService.findAll();
    }

    @ApiOperation(value = "Visualize one product by id.")
    @GetMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Product> finById(@PathVariable @Min(1) Long id) {
        return productService.findById(id);
    }

    @ApiOperation(value = "Create a new product.")
    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@Valid @RequestBody ProductDTO productDTO) {
        return productService.create(productDTO);
    }

    @ApiOperation(value = "Update an existing product.")
    @PutMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product updateProduct(@RequestBody ProductDTO productDTO, @PathVariable Long id) {
        return productService.save(productDTO, id);
    }

    @ApiOperation(value = "Delete a product by id.")
    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }
}
