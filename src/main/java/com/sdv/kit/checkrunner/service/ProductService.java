package com.sdv.kit.checkrunner.service;

import com.sdv.kit.checkrunner.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> findById(Long id);
    List<Product> findAll();
    Product save(Product product);
    void deleteById(Long id);
    Product update(Long id, Product product);
}
