package com.example.ex7restfulapi.model.dto;

import javax.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseRequestDTO {

    @NotBlank(message = "Warehouse code cannot be blank")
    private String warehouseCode;

    @NotBlank(message = "Warehouse name cannot be blank")
    private String warehouseName;

    private String location;
}
