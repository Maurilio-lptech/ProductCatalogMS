package com.example.ProductCatalogMS.projection;

public interface ProductListItem {
    String getId();

    String getName();

    String getDescription();

    double getPrice();

    int getAvailableQuantity();

    String getCategory();

}
