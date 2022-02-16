package com.coderhouse.builder;

import com.coderhouse.model.database.document.CartItemDocument;
import com.coderhouse.model.request.CartItemRequest;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class CartItemBuilder {
    public static CartItemDocument requestToEntityCartItem(CartItemRequest request) {
        return CartItemDocument.builder()
                .amount(request.getAmount())
                .productCode(request.getProductCode())
                .build();
    }

}
