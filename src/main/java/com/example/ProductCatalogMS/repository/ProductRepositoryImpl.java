package com.example.ProductCatalogMS.repository;

import com.example.ProductCatalogMS.model.Product;
import com.example.ProductCatalogMS.projection.ProductListItem;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@AllArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public Page<ProductListItem> findAllProjected(Pageable pageable) {

        Query query = new Query();

        long totalCount = mongoTemplate.count(query, Product.class);

        query.with(pageable);

        List<ProductListItem> content = mongoTemplate.find(
                query,
                ProductListItem.class, // <-- Proietta direttamente sul tipo di Proiezione
                "product" // Nome della collezione
        );

        // uso l'interfaccia PageImpl per creare l'oggetto da restituire
        return new PageImpl<>(content, pageable, totalCount);
    }


}