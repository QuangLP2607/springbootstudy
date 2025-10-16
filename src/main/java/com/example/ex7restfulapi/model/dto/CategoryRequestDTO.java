package com.example.ex7restfulapi.model.dto;

import javax.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryRequestDTO {

    @NotBlank(message = "Category code must not be blank")
    private String categoryCode;

    @NotBlank(message = "Category name must not be blank")
    private String categoryName;

    private String description;
}
