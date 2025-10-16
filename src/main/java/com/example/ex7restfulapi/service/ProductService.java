package com.example.ex7restfulapi.service;

import com.example.ex7restfulapi.common.ApiException;
import com.example.ex7restfulapi.common.ErrorCode;
import com.example.ex7restfulapi.model.dto.ProductRequestDTO;
import com.example.ex7restfulapi.model.dto.ProductResponseDTO;
import com.example.ex7restfulapi.model.entity.Product;
import com.example.ex7restfulapi.model.mapper.ProductMapper;
import com.example.ex7restfulapi.repository.ProductRepository;
import com.example.ex7restfulapi.repository.CategoryRepository;
import com.example.ex7restfulapi.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    private final WarehouseRepository warehouseRepository;

    /**
     * Create a new product.
     * Checks for duplicate product code and ensures referenced category and warehouse exist.
     */
    @Transactional
    public ProductResponseDTO createProduct(ProductRequestDTO dto) {

        // Check for duplicate product code
        if (productRepository.existsByProductCode(dto.getProductCode())) {
            throw new ApiException(ErrorCode.CONFLICT, "Product code already exists");
        }

        // Verify that the referenced Category exists
        if (!categoryRepository.existsById(dto.getCategoryId())) {
            throw new ApiException(ErrorCode.NOT_FOUND, "Category not found");
        }

        // Verify that the referenced Warehouse exists
        if (!warehouseRepository.existsById(dto.getWarehouseId())) {
            throw new ApiException(ErrorCode.NOT_FOUND, "Warehouse not found");
        }

        // Map DTO to entity and save
        Product product = productMapper.toEntity(dto);
        Product saved = productRepository.save(product);
        return productMapper.toResponseDTO(saved);
    }

    /**
     * Get a single product by ID.
     * Throws NOT_FOUND exception if product does not exist.
     */
    @Transactional(readOnly = true)
    public ProductResponseDTO getProductById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, "Product not found"));
        return productMapper.toResponseDTO(product);
    }

    /**
     * Get products filtered by name with pagination.
     * Uses Spring Data Pageable (10 items per page, sorted by ID ascending).
     */
    @Transactional(readOnly = true)
    public List<ProductResponseDTO> getProductsByName(String name, int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("id").ascending());
        Page<Product> productPage = productRepository.findByProductNameContainingIgnoreCase(name, pageable);

        // Map entities to DTOs
        return productPage.stream()
                .map(productMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Update an existing product.
     * Checks for product existence, duplicate codes, and existence of category/warehouse.
     */
    @Transactional
    public ProductResponseDTO updateProduct(Integer id, ProductRequestDTO dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, "Product not found"));

        // Prevent duplicate product code
        if (!product.getProductCode().equals(dto.getProductCode())
                && productRepository.existsByProductCode(dto.getProductCode())) {
            throw new ApiException(ErrorCode.CONFLICT, "Product code already exists");
        }

        // Verify referenced Category and Warehouse exist
        if (!categoryRepository.existsById(dto.getCategoryId())) {
            throw new ApiException(ErrorCode.NOT_FOUND, "Category not found");
        }
        if (!warehouseRepository.existsById(dto.getWarehouseId())) {
            throw new ApiException(ErrorCode.NOT_FOUND, "Warehouse not found");
        }

        // Update fields
        product.setProductCode(dto.getProductCode());
        product.setProductName(dto.getProductName());
        product.setCategoryId(dto.getCategoryId());
        product.setWarehouseId(dto.getWarehouseId());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setImagePath(dto.getImagePath());
        product.setStockQuantity(dto.getStockQuantity());

        Product updated = productRepository.save(product);
        return productMapper.toResponseDTO(updated);
    }

    /**
     * Delete a product by ID.
     * Throws NOT_FOUND exception if product does not exist.
     */
    @Transactional
    public void deleteProduct(Integer id) {
        if (!productRepository.existsById(id)) {
            throw new ApiException(ErrorCode.NOT_FOUND, "Product not found");
        }
        productRepository.deleteById(id);
    }
}
