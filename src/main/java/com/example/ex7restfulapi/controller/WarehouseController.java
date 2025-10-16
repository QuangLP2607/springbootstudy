package com.example.ex7restfulapi.controller;

import com.example.ex7restfulapi.common.ApiResponse;
import com.example.ex7restfulapi.common.ErrorCode;
import com.example.ex7restfulapi.model.dto.WarehouseRequestDTO;
import com.example.ex7restfulapi.model.dto.WarehouseResponseDTO;
import com.example.ex7restfulapi.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/warehouses")
@RequiredArgsConstructor
public class WarehouseController {

    private final WarehouseService warehouseService;

    @PostMapping
    public ResponseEntity<ApiResponse<WarehouseResponseDTO>> createWarehouse(
            @Validated @RequestBody WarehouseRequestDTO dto) {
        WarehouseResponseDTO created = warehouseService.createWarehouse(dto);
        return ResponseEntity.status(ErrorCode.CREATED.code())
                .body(ApiResponse.success(ErrorCode.CREATED, created));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<WarehouseResponseDTO>>> getAllWarehouses() {
        return ResponseEntity.ok(ApiResponse.success(ErrorCode.SUCCESS, warehouseService.getAllWarehouses()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<WarehouseResponseDTO>> getWarehouseById(@PathVariable Integer id) {
        return ResponseEntity.ok(ApiResponse.success(ErrorCode.SUCCESS, warehouseService.getWarehouseById(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<WarehouseResponseDTO>> updateWarehouse(
            @PathVariable Integer id,
            @Validated @RequestBody WarehouseRequestDTO dto) {
        WarehouseResponseDTO updated = warehouseService.updateWarehouse(id, dto);
        return ResponseEntity.ok(ApiResponse.success(ErrorCode.UPDATED, updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteWarehouse(@PathVariable Integer id) {
        warehouseService.deleteWarehouse(id);
        return ResponseEntity.ok(ApiResponse.success(ErrorCode.DELETED, null));
    }
}
