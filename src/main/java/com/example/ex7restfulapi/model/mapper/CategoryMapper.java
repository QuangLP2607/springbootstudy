package com.example.ex7restfulapi.model.mapper;

import com.example.ex7restfulapi.model.dto.CategoryRequestDTO;
import com.example.ex7restfulapi.model.dto.CategoryResponseDTO;
import com.example.ex7restfulapi.model.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toEntity(CategoryRequestDTO dto);

    CategoryResponseDTO toResponseDTO(Category entity);
}
