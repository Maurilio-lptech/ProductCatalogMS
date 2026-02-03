package com.example.ProductCatalogMS.controller;

import com.example.ProductCatalogMS.dto.ProductDto;
import com.example.ProductCatalogMS.projection.ProductListItem;
import com.example.ProductCatalogMS.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/products")
@Tag(name = "Product catalog manager", description = "CRUD operation for products.")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping
    @Operation(summary = "Retrieve a list of all products whit pagination",
            description = "Response whit a page of productListItem objects.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product list retrieve whit success.")
    })
    ResponseEntity<Page<ProductListItem>> findAll(Pageable pageable) {
        log.info("Call the endpoint, Find all, received");
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a product through Id",
            description = "Response a ProductDto for id in input.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product retrieve whit success."),
            @ApiResponse(responseCode = "404", description = "PRODUCT NOT FOUND")
    })
    ResponseEntity<ProductDto> findById(
            @Parameter(description = "ID unique of research product", example = "ABC12312")
            @PathVariable String id) {
        log.info("Call the endpoint, find by id, received");
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @Operation(summary = "Add a product",
            description = "Create a new product in catalog and respond whit the new object created.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product Created whit success."),
            @ApiResponse(responseCode = "400", description = "No valid input"),
    })
    ResponseEntity<ProductDto> add(ProductDto productDto) {
        log.info("Call the endpoint for add new product, received");
        return new ResponseEntity<>(service.add(productDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modify a product",
            description = "Modified a product and respond whit modified object.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product modified whit success."),
            @ApiResponse(responseCode = "400", description = "No valid input"),
            @ApiResponse(responseCode = "404", description = "PRODUCT NOT FOUND")
    })
    ResponseEntity<ProductDto> put(ProductDto productDto,
                                   @Parameter(description = "ID unique of product to be modified", example = "ABC12312")
                                   @PathVariable String id) {
        log.info("Call the endpoint for modify a product, received");
        return ResponseEntity.ok(service.put(productDto, id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove one product whit Id",
            description = "Delete a product whit Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product removed whit success. No Content."),
            @ApiResponse(responseCode = "404", description = "PRODUCT NOT FOUND")
    })
    ResponseEntity<Void> remove(
            @Parameter(description = "ID unique of product to be remove", example = "ABC12312")
            @PathVariable String id) {
        log.info("Call the endpoint for remove a product, received");
        service.remove(id);
        log.debug("Product product has been removed id: {}", id);
        return ResponseEntity.noContent().build();
    }


}
