package com.challenge.productmanagement.controller;

import com.challenge.productmanagement.dto.ProductDTO;
import com.challenge.productmanagement.model.Product;
import com.challenge.productmanagement.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    @GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Product> findAll() {
        return productService.findAll();
    }

    @ApiOperation(value = "Visualize one product by id.")
    @GetMapping(value = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Optional<Product> findById(@PathVariable @Min(1) Long id) {
        return productService.findById(id);
    }

    @ApiOperation(value = "Create a new product.")
    @PostMapping(value = "/products", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@Valid @RequestBody ProductDTO productDTO) {
        return productService.create(productDTO);
    }

    @ApiOperation(value = "Update an existing product.")
    @PutMapping(value = "/products/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Product updateProduct(@Valid @RequestBody ProductDTO productDTO, @PathVariable Long id) {
        return productService.save(productDTO, id);
    }

    @ApiOperation(value = "Delete a product by id.")
    @DeleteMapping(value = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        productService.delete(id);
    }
}
