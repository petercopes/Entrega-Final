package com.coderhouse.model;

import com.coderhouse.builder.CartBuilder;
import com.coderhouse.model.database.document.CartDocument;

import com.coderhouse.model.response.CartResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CartFactory {

    public CartDocument createCart(String address, String email) {
        return CartBuilder.cartDocumentCreate(address,email);

    }
    public CartResponse createCartResponse(CartDocument document){
      return CartBuilder.cartEntityToResponse(document);
    }

}
