package com.example.ProductCatalogMS.service;


import com.example.ProductCatalogMS.dto.ProductDto;
import com.example.ProductCatalogMS.mapper.ProductMapper;
import com.example.ProductCatalogMS.model.Product;
import com.example.ProductCatalogMS.projection.ProductListItem;
import com.example.ProductCatalogMS.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

@Slf4j
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        log.info("Fetching a page of products with pageable: {}", pageable);
        Page<Product> products = productRepository.findAll(pageable);
        log.info("Found {} products in the current page", products.getNumberOfElements());
        return products;
    }

    @Override
    public ProductDto findById(String id) {
        log.info("Searching for product with id: {}", id);
        return mapper.toDto(productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product whit id: " + id + " Not found")));
    }

    @Override
    public ProductDto add(ProductDto productDto) {
        log.info("Start method to add a new product");
        Product productToSave = mapper.toEntity(productDto);
        return mapper.toDto(productRepository.save(productToSave));
    }

    @Override
    public ProductDto put(ProductDto productDto, String id) {
        log.info("Attempting to update product with id: {}", id);
        Product existingProduct = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product with id: " + id + " not found"));

        existingProduct.setName(productDto.getName());
        existingProduct.setCategory(productDto.getCategory());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setDescription(productDto.getDescription());
        existingProduct.setAvailableQuantity(productDto.getAvailableQuantity());

        return mapper.toDto(productRepository.save(existingProduct));
    }

    @Override
    public void remove(String id) {
        log.info("Attempting to delete product with id: {}", id);
        try {
            productRepository.deleteById(id);
            log.info("Product with id: {} successfully deleted", id);
        } catch (Exception e) {
            log.error("Error in delete method for id: {} ", id, e);
            throw new RuntimeException(e);
        }
    }
}
