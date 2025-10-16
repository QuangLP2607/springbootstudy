package com.example.ex7restfulapi.service;

import com.example.ex7restfulapi.common.ApiException;
import com.example.ex7restfulapi.common.ErrorCode;
import com.example.ex7restfulapi.model.dto.WarehouseRequestDTO;
import com.example.ex7restfulapi.model.dto.WarehouseResponseDTO;
import com.example.ex7restfulapi.model.entity.Warehouse;
import com.example.ex7restfulapi.model.mapper.WarehouseMapper;
import com.example.ex7restfulapi.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final WarehouseMapper warehouseMapper;

    /**
     * Create a new warehouse.
     * Validates that the warehouse code and name are unique.
     */
    @Transactional
    public WarehouseResponseDTO createWarehouse(WarehouseRequestDTO dto) {
        // Check for duplicate warehouse code
        if (warehouseRepository.existsByWarehouseCode(dto.getWarehouseCode())) {
            throw new ApiException(ErrorCode.CONFLICT, "Warehouse code already exists");
        }

        // Check for duplicate warehouse name
        if (warehouseRepository.existsByWarehouseName(dto.getWarehouseName())) {
            throw new ApiException(ErrorCode.CONFLICT, "Warehouse name already exists");
        }

        // Map DTO to entity and save
        Warehouse warehouse = warehouseMapper.toEntity(dto);
        Warehouse saved = warehouseRepository.save(warehouse);
        return warehouseMapper.toResponseDTO(saved);
    }

    /**
     * Get all warehouses.
     */
    @Transactional(readOnly = true)
    public List<WarehouseResponseDTO> getAllWarehouses() {
        return warehouseRepository.findAll()
                .stream()
                .map(warehouseMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get a single warehouse by ID.
     * Throws NOT_FOUND if warehouse does not exist.
     */
    @Transactional(readOnly = true)
    public WarehouseResponseDTO getWarehouseById(Integer id) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, "Warehouse not found"));
        return warehouseMapper.toResponseDTO(warehouse);
    }

    /**
     * Update an existing warehouse.
     * Throws NOT_FOUND if warehouse does not exist.
     */
    @Transactional
    public WarehouseResponseDTO updateWarehouse(Integer id, WarehouseRequestDTO dto) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND, "Warehouse not found"));

        // Update fields
        warehouse.setWarehouseCode(dto.getWarehouseCode());
        warehouse.setWarehouseName(dto.getWarehouseName());
        warehouse.setLocation(dto.getLocation());

        Warehouse updated = warehouseRepository.save(warehouse);
        return warehouseMapper.toResponseDTO(updated);
    }

    /**
     * Delete a warehouse by ID.
     * Throws NOT_FOUND if warehouse does not exist.
     */
    @Transactional
    public void deleteWarehouse(Integer id) {
        if (!warehouseRepository.existsById(id)) {
            throw new ApiException(ErrorCode.NOT_FOUND, "Warehouse not found");
        }
        warehouseRepository.deleteById(id);
    }
}
