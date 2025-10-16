package com.example.ex7restfulapi.model.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponseDTO {
    private Integer id;
    private String categoryCode;
    private String categoryName;
    private String description;
}
