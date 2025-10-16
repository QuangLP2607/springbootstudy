package com.example.ex7restfulapi.repository;

import com.example.ex7restfulapi.model.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
    boolean existsByWarehouseCode(String warehouseCode);
    boolean existsByWarehouseName(String warehouseName);
}
