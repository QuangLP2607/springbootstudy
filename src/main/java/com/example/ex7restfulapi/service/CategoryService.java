package com.example.ex7restfulapi.service;

import com.example.ex7restfulapi.common.ApiException;
import com.example.ex7restfulapi.common.ErrorCode;
import com.example.ex7restfulapi.model.dto.CategoryRequestDTO;
import com.example.ex7restfulapi.model.dto.CategoryResponseDTO;
import com.example.ex7restfulapi.model.entity.Category;
import com.example.ex7restfulapi.model.mapper.CategoryMapper;
import com.example.ex7restfulapi.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    /**
     * Create a new category.
     * Validates that the category code and name are unique.
     */
    @Transactional
    public CategoryResponseDTO createCategory(CategoryRequestDTO dto) {
        // Check for duplicate category code
        if (categoryRepository.existsByCategoryCode(dto.getCategoryCode())) {
            throw new ApiException(ErrorCode.CONFLICT, "Category code already exists");
        }

        // Check for duplicate category name
        if (categoryRepository.existsByCategoryName(dto.getCategoryName())) {
            throw new ApiException(ErrorCode.CONFLICT, "Category name already exists");
        }

        // Map DTO to entity and save
        Category category = categoryMapper.toEntity(dto);
        Category saved = categoryRepository.save(category);
        return categoryMapper.toResponseDTO(saved);
    }

    /**
     * Get all categories.
     */
    @Transactional(readOnly = true)
    public List<CategoryResponseDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get a single category by ID.
     * Throws NOT_FOUND if category does not exist.
     */
    @Transactional(readOnly = true)
    public CategoryResponseDTO getCategoryById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, "Category not found"));
        return categoryMapper.toResponseDTO(category);
    }

    /**
     * Update an existing category.
     * Throws NOT_FOUND if category does not exist.
     */
    @Transactional
    public CategoryResponseDTO updateCategory(Integer id, CategoryRequestDTO dto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, "Category not found"));

        // Update fields
        category.setCategoryCode(dto.getCategoryCode());
        category.setCategoryName(dto.getCategoryName());
        category.setDescription(dto.getDescription());

        Category updated = categoryRepository.save(category);
        return categoryMapper.toResponseDTO(updated);
    }

    /**
     * Delete a category by ID.
     * Throws NOT_FOUND if category does not exist.
     */
    @Transactional
    public void deleteCategory(Integer id) {
        if (!categoryRepository.existsById(id)) {
            throw new ApiException(ErrorCode.NOT_FOUND, "Category not found");
        }
        categoryRepository.deleteById(id);
    }
}
