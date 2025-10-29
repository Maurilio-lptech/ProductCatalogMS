package com.example.ProductCatalogMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO) // for support pagination in findALlProducts
public class ProductCatalogMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductCatalogMsApplication.class, args);
	}

}
