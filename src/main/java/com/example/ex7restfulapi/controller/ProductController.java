package com.example.ex7restfulapi.controller;

import com.example.ex7restfulapi.common.ApiResponse;
import com.example.ex7restfulapi.common.ErrorCode;
import com.example.ex7restfulapi.model.dto.ProductRequestDTO;
import com.example.ex7restfulapi.model.dto.ProductResponseDTO;
import com.example.ex7restfulapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponseDTO>> createProduct(
            @Validated @RequestBody ProductRequestDTO dto) {
        ProductResponseDTO created = productService.createProduct(dto);
        return ResponseEntity.status(ErrorCode.CREATED.code())
                .body(ApiResponse.success(ErrorCode.CREATED, created));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponseDTO>>> getProducts(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "0") int page
    ) {
        List<ProductResponseDTO> products = productService.getProductsByName(name, page);
        return ResponseEntity.ok(ApiResponse.success(ErrorCode.SUCCESS, products));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDTO>> getProductById(@PathVariable Integer id) {
        return ResponseEntity.ok(ApiResponse.success(ErrorCode.SUCCESS, productService.getProductById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDTO>> updateProduct(
            @PathVariable Integer id,
            @Validated @RequestBody ProductRequestDTO dto) {
        ProductResponseDTO updated = productService.updateProduct(id, dto);
        return ResponseEntity.ok(ApiResponse.success(ErrorCode.UPDATED, updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok(ApiResponse.success(ErrorCode.DELETED, null));
    }
}
