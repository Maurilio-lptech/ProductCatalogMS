package com.example.ProductCatalogMS.repository;

import com.example.ProductCatalogMS.model.Product;
import com.example.ProductCatalogMS.projection.ProductListItem;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public Page<Product> findAll(Pageable pageable) {

        Query query = new Query();

        long totalCount = mongoTemplate.count(query, Product.class);

        query.with(pageable);

        List<Product> content = mongoTemplate.find(
                query,
                Product.class, // <-- Proietta direttamente sul tipo di Proiezione
                "products" // Nome della collezione
        );

        // uso l'interfaccia PageImpl per creare l'oggetto da restituire
        return new PageImpl<>(content, pageable, totalCount);
    }


}