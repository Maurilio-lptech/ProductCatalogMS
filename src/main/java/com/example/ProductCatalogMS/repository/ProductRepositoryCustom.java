package com.example.ProductCatalogMS.repository;

import com.example.ProductCatalogMS.model.Product;
import com.example.ProductCatalogMS.projection.ProductListItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryCustom {
    Page<Product> findAll(Pageable pageable);
}
