package com.example.ex7restfulapi.model.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Product code cannot be blank")
    @Column(name = "product_code", nullable = false, unique = true)
    private String productCode;

    @NotNull(message = "Category ID cannot be null")
    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    @NotNull(message = "Warehouse ID cannot be null")
    @Column(name = "warehouse_id", nullable = false)
    private Integer warehouseId;

    @NotBlank(message = "Product name cannot be blank")
    @Column(name = "product_name", nullable = false)
    private String productName;

    @Positive(message = "Price must be greater than 0")
    @Column(nullable = false)
    private Double price;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    private String imagePath;

    @Min(value = 0, message = "Stock quantity cannot be negative")
    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity = 0;

    @Min(value = 0, message = "Sold quantity cannot be negative")
    @Column(name = "sold_quantity", nullable = false)
    private Integer soldQuantity = 0;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        if (this.stockQuantity == null) this.stockQuantity = 0;
        if (this.soldQuantity == null) this.soldQuantity = 0;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
