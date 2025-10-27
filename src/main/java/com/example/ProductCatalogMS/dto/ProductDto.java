package com.example.ProductCatalogMS.dto;

import lombok.Data;

@Data
public class ProductDto {

    private String id;
    private String name;
    private String description;
    private double price;
    private int availableQuantity;
    private String category;

}
