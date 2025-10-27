package com.example.ProductCatalogMS.service;

import com.example.ProductCatalogMS.dto.ProductDto;

import com.example.ProductCatalogMS.projection.ProductListItem;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<ProductListItem> findAll(Pageable pageable);
    ProductDto findById(String id);
    ProductDto add(ProductDto productDto);
    ProductDto put(ProductDto productDto, String id);
    void remove(String id);

}
