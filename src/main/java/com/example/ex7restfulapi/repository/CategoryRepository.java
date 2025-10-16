package com.example.ex7restfulapi.repository;

import com.example.ex7restfulapi.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByCategoryCode(String categoryCode);
    boolean existsByCategoryName(String categoryName);
}
