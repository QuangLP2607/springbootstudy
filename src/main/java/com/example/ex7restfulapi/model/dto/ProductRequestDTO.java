package com.example.ex7restfulapi.model.dto;

import lombok.*;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDTO {

    @NotBlank(message = "Product code cannot be blank")
    private String productCode;

    @NotNull(message = "Category ID cannot be null")
    private Integer categoryId;

    @NotNull(message = "Warehouse ID cannot be null")
    private Integer warehouseId;

    @NotBlank(message = "Product name cannot be blank")
    private String productName;

    @Positive(message = "Price must be greater than 0")
    private Double price;

    private String description;
    private String imagePath;

    @Min(value = 0, message = "Stock quantity cannot be negative")
    private Integer stockQuantity;

    @Min(value = 0, message = "Sold quantity cannot be negative")
    private Integer soldQuantity;
}
