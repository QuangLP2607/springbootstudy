package com.example.ex7restfulapi.model.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseResponseDTO {
    private Integer id;
    private String warehouseCode;
    private String warehouseName;
    private String location;
}
