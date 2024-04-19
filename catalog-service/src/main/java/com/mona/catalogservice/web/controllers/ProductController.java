package com.mona.catalogservice.web.controllers;

import com.mona.catalogservice.domain.PagedResult;
import com.mona.catalogservice.domain.Product;
import com.mona.catalogservice.domain.ProductService;
import com.mona.catalogservice.exception.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
class ProductController {

    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    // getapi to return List<products> from repository
    // pagination to display 10 records per page or 1 by default (request param)
    @GetMapping
    PagedResult<Product> getProducts(@RequestParam(name = "page", defaultValue = "1") int pageno) {
        return productService.getProducts(pageno);
    }

    @GetMapping("/{code}")
    ResponseEntity<Product> getProductsByCode(@PathVariable String code) {
        return productService.getProductsByCode(code)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> ProductNotFoundException.forCode(code));
    }
}
