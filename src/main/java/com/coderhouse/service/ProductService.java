package com.coderhouse.service;

import com.coderhouse.model.request.ProductRequest;
import com.coderhouse.model.response.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse create(ProductRequest request) throws Exception;
    ProductResponse update(String id, ProductRequest request)  throws Exception;
    List<ProductResponse> searchAll();
    List<ProductResponse> searchByCategory(String category)  throws Exception;
    ProductResponse getByCode(String productCode)  throws Exception;
    ProductResponse deleteProduct(String id)  throws Exception;
}
