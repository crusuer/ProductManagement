package com.challenge.productmanagement.service;

import com.challenge.productmanagement.dto.ProductDTO;
import com.challenge.productmanagement.model.Product;

import java.util.Optional;

public interface ProductService {
    Product create(ProductDTO productDTO);

    Optional<Product> save(ProductDTO productDTO, Long id);

    Optional<Product> findById(Long id);

    Iterable<Product> findAll();

    void delete(Long id);
}
