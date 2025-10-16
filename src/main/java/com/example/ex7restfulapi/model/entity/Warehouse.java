package com.example.ex7restfulapi.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warehouse")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Warehouse code cannot be blank")
    @Column(name = "warehouse_code", nullable = false, unique = true)
    private String warehouseCode;

    @NotBlank(message = "Warehouse name cannot be blank")
    @Column(name = "warehouse_name", nullable = false)
    private String warehouseName;

    private String location;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
