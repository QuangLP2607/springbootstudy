package com.example.ex7restfulapi.repository;

import com.example.ex7restfulapi.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    boolean existsByProductCode(String productCode);
    Page<Product> findByProductNameContainingIgnoreCase(String name, Pageable pageable);
}
