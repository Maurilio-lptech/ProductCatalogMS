package com.example.ProductCatalogMS.serviceTest;

import com.example.ProductCatalogMS.dto.ProductDto;
import com.example.ProductCatalogMS.mapper.ProductMapper;
import com.example.ProductCatalogMS.model.Product;
import com.example.ProductCatalogMS.projection.ProductListItem;
import com.example.ProductCatalogMS.repository.ProductRepository;
import com.example.ProductCatalogMS.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = ProductServiceImpl.class)
public class ProductServiceImplTest {

    @MockitoBean
    private ProductRepository productRepository;
    @MockitoBean
    private ProductMapper mapper;

    @Autowired
    ProductServiceImpl service;

    String productId;
    Product product;
    ProductDto productDto;

    @BeforeEach
    void initial() {
        this.productId = UUID.randomUUID().toString();

        this.product = new Product();
        product.setId(productId);
        product.setName("Test product");
        product.setCategory("Test category");
        product.setDescription("Test description");
        product.setAvailableQuantity(10);
        product.setPrice(1.99);

        this.productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setCategory(product.getCategory());
        productDto.setDescription(product.getDescription());
        productDto.setAvailableQuantity(product.getAvailableQuantity());
        productDto.setPrice(product.getPrice());
    }

    @Test
    public void FindbyId_WhenProductExist() {

        when(productRepository.findById(productId))
                .thenReturn(Optional.of(product));

        when(mapper.toDto(product)).thenReturn(productDto);

        ProductDto response = service.findById(productId);

        verify(productRepository, times(1)).findById(productId);
        assertEquals(productId, response.getId());
    }

    @Test
    public void FindbyId_WhenProductNotExist() {

        when(productRepository.findById(productId))
                .thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> {
            service.findById(productId);
        }, "launch an exception for no find product");

        verify(productRepository, times(1)).findById(product.getId());

    }

}
