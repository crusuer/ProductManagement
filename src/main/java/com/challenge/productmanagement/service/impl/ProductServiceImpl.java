package com.challenge.productmanagement.service.impl;

import com.challenge.productmanagement.dto.ProductDTO;
import com.challenge.productmanagement.model.Product;
import com.challenge.productmanagement.repository.ProductRepository;
import com.challenge.productmanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());

        return productRepository.save(product);
    }

    @Override
    public Optional<Product> save(ProductDTO productDTO, Long id) {
        return findById(id).map(x -> {
            x.setName(productDTO.getName());
            x.setPrice(productDTO.getPrice());
            return productRepository.save(x);
        });
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
