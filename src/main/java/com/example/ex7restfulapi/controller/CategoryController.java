package com.example.ex7restfulapi.controller;

import com.example.ex7restfulapi.common.ApiResponse;
import com.example.ex7restfulapi.common.ErrorCode;
import com.example.ex7restfulapi.model.dto.CategoryRequestDTO;
import com.example.ex7restfulapi.model.dto.CategoryResponseDTO;
import com.example.ex7restfulapi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponseDTO>> createCategory(
            @Validated @RequestBody CategoryRequestDTO dto) {
        CategoryResponseDTO created = categoryService.createCategory(dto);
        return ResponseEntity.status(ErrorCode.CREATED.code())
                .body(ApiResponse.success(ErrorCode.CREATED, created));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponseDTO>>> getAllCategories() {
        return ResponseEntity.ok(ApiResponse.success(ErrorCode.SUCCESS, categoryService.getAllCategories()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponseDTO>> getCategoryById(@PathVariable Integer id) {
        return ResponseEntity.ok(ApiResponse.success(ErrorCode.SUCCESS, categoryService.getCategoryById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponseDTO>> updateCategory(
            @PathVariable Integer id,
            @Validated @RequestBody CategoryRequestDTO dto) {
        CategoryResponseDTO updated = categoryService.updateCategory(id, dto);
        return ResponseEntity.ok(ApiResponse.success(ErrorCode.UPDATED, updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(ApiResponse.success(ErrorCode.DELETED, null));
    }
}
