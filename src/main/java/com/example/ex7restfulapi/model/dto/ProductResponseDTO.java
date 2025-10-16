package com.example.ex7restfulapi.model.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDTO {
    private Integer id;
    private String productCode;
    private Integer categoryId;
    private Integer warehouseId;
    private String productName;
    private Double price;
    private String description;
    private String imagePath;
    private Integer stockQuantity;
    private Integer soldQuantity;
}
