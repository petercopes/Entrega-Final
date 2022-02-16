package com.coderhouse.controller;

import com.coderhouse.model.request.ProductRequest;
import com.coderhouse.model.response.ProductResponse;
import com.coderhouse.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {

    private final ProductService service;

    @PostMapping("/products")
    public ResponseEntity<ProductResponse> createProduct(
            @Validated @RequestBody ProductRequest product) {
        try {
            return
                   new ResponseEntity<ProductResponse>(service.create(product), HttpStatus.OK);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }

    }
    @GetMapping("/products/category/{category}")
    public ResponseEntity<List<ProductResponse>> searchProductByCategory(@PathVariable String category) {

        try {
            return new ResponseEntity<>(service.searchByCategory(category),HttpStatus.OK);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }
    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> searchProduct() {

        try {
            return new ResponseEntity<>(service.searchAll(),HttpStatus.OK);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponse> getByProductCode(@PathVariable String id){
        try {
            return new ResponseEntity<>(service.getByCode(id),HttpStatus.OK);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable String id,
            @Validated @RequestBody ProductRequest product) {

        try {
            return new ResponseEntity<>(service.update(id, product),HttpStatus.ACCEPTED);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }
    @DeleteMapping("/products/{id}")
    public ResponseEntity<ProductResponse> deleteProduct(@PathVariable String id){
        try {
            return new ResponseEntity<>(service.deleteProduct(id),HttpStatus.OK);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }


}
