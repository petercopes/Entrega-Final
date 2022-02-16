package com.coderhouse.builder;

import com.coderhouse.model.database.document.ProductDocument;
import com.coderhouse.model.request.ProductRequest;
import com.coderhouse.model.response.ProductResponse;
import java.util.ArrayList;
import java.util.List;

public class ProductBuilder {

    public static ProductDocument requestToEntityProduct(ProductRequest request) {
        return ProductDocument.builder()
                .name(request.getName())
                .price(request.getPrice())
                .code(request.getCode())
                .category(request.getCategory())
                .description(request.getDescription())
                .build();
    }



    public static ProductResponse entityToResponseCreate(ProductDocument entity) {
        return ProductResponse.builder()
                .code(entity.getCode())
                .name(entity.getName())
                .category(entity.getCategory())
                .price(entity.getPrice())
                .build();
    }

    public static List<ProductResponse>
    listEntityToResponse(List<ProductDocument> products) {

        var listResponse = new ArrayList<ProductResponse>();
        products.forEach(item -> listResponse.add(entityToResponseCreate(item)));
        return listResponse;
    }

}
