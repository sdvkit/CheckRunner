package com.sdv.kit.checkrunner.repository;

import com.sdv.kit.checkrunner.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}