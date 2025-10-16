package com.example.ex7restfulapi.model.mapper;

import com.example.ex7restfulapi.model.dto.WarehouseRequestDTO;
import com.example.ex7restfulapi.model.dto.WarehouseResponseDTO;
import com.example.ex7restfulapi.model.entity.Warehouse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {
    Warehouse toEntity(WarehouseRequestDTO dto);

    WarehouseResponseDTO toResponseDTO(Warehouse entity);
}
