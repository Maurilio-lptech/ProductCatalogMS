package com.example.ProductCatalogMS.service;

import com.example.ProductCatalogMS.dto.ProductDto;
import com.example.ProductCatalogMS.mapper.ProductMapper;
import com.example.ProductCatalogMS.model.Product;
import com.example.ProductCatalogMS.projection.ProductListItem;
import com.example.ProductCatalogMS.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    @Override
    public Page<ProductListItem> findAll(Pageable pageable) {
        return productRepository.findAllProjected(pageable);
    }

    @Override
    public ProductDto findById(String id) {
        return mapper.toDto(
                productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product whit id: " + id + " Not found")));
    }

    @Override
    public ProductDto add(ProductDto productDto) {
        Product productToSave = mapper.toEntity(productDto);
        return mapper.toDto(productRepository.save(productToSave));
    }

    @Override
    public ProductDto put(ProductDto productDto, String id) {
        Product existingProduct = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product whit id: " + id + " Not found"));

        existingProduct.setName(productDto.getName());
        existingProduct.setCategory(productDto.getCategory());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setDescription(productDto.getDescription());
        existingProduct.setAvailableQuantity(productDto.getAvailableQuantity());

        return mapper.toDto(productRepository.save(existingProduct));
    }

    @Override
    public void remove(String id) {
        if (productRepository.existsById(id))
            productRepository.deleteById(id);
        else
            throw new IllegalArgumentException("Product whit id: " + id + " Not found");
    }
}
