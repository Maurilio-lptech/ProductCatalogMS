package com.example.ProductCatalogMS.mapper;

import com.example.ProductCatalogMS.dto.ProductDto;
import com.example.ProductCatalogMS.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto toDto(Product product);
    Product toEntity(ProductDto productDto);

}
