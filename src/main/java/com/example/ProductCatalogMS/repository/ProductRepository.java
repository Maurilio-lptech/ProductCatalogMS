package com.example.ProductCatalogMS.repository;

import com.example.ProductCatalogMS.model.Product;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends MongoRepository<Product,String> , ProductRepositoryCustom{

}
