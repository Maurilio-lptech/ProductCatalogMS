package com.example.ProductCatalogMS.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductDto {

    private String id;

    @NotBlank(message = "The product name is required.")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters.")
    private String name;

    @NotBlank(message = "The description is required.")
    @Size(max = 500, message = "Description cannot exceed 500 characters.")
    private String description;

    @NotNull(message = "Price is required.")
    @DecimalMin(value = "0.01", message = "Price must be greater than zero.")
    private double price;

    @NotNull(message = "Available quantity is required.")
    @Min(value = 0, message = "Quantity cannot be negative.")
    private int availableQuantity;

    @NotBlank(message = "The category is required.")
    private String category;

}
