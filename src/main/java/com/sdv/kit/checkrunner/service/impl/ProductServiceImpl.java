package com.sdv.kit.checkrunner.service.impl;

import com.sdv.kit.checkrunner.model.Product;
import com.sdv.kit.checkrunner.repository.ProductRepository;
import com.sdv.kit.checkrunner.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Product update(Long id, Product productParam) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(productParam.getName());
                    product.setDiscount(productParam.getDiscount());
                    product.setPrice(productParam.getPrice());
                    return product;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
