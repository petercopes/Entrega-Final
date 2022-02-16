package com.coderhouse.model;

import com.coderhouse.builder.CartItemBuilder;
import com.coderhouse.model.database.document.CartItemDocument;
import com.coderhouse.model.request.CartItemRequest;

import lombok.Data;

@Data
public class CartItemFactory {
    public CartItemDocument createCartItem(CartItemRequest request) {
        return CartItemBuilder.requestToEntityCartItem(request);
    }

}
