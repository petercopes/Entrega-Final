package com.coderhouse.model;

import com.coderhouse.builder.ProductBuilder;
import com.coderhouse.model.database.document.ProductDocument;
import com.coderhouse.model.request.ProductRequest;
import lombok.Data;

@Data
public class ProductFactory {

    public ProductDocument createProduct(ProductRequest request) {
        return ProductBuilder.requestToEntityProduct(request);

    }

}
