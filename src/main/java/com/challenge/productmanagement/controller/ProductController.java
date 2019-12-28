package com.challenge.productmanagement.controller;

import com.challenge.productmanagement.dto.ProductDTO;
import com.challenge.productmanagement.model.Product;
import com.challenge.productmanagement.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@Validated
public class ProductController {
    @Autowired
    private ProductService productService;

    @ApiOperation(value = "Get a list of all products")
    @GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = Iterable.class)
    })
    public ResponseEntity<Iterable<Product>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @ApiOperation(value = "Create a new product")
    @PostMapping(value = "/products", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Product Created", response = Product.class)
    })
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(productDTO));
    }

    @ApiOperation(value = "Update an existing product")
    @PutMapping(value = "/products/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product Updated", response = Optional.class)
    })
    public ResponseEntity<Optional<Product>> updateProduct(@Valid @RequestBody ProductDTO productDTO, @PathVariable Long id) {
        return ResponseEntity.ok(productService.save(productDTO, id));
    }
}
