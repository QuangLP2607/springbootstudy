package com.example.ex7restfulapi.model.mapper;

import com.example.ex7restfulapi.model.dto.*;
import com.example.ex7restfulapi.model.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductRequestDTO dto);

    ProductResponseDTO toResponseDTO(Product product);
}
