package com.coderhouse.builder;

import com.coderhouse.model.database.document.CartDocument;
import com.coderhouse.model.database.document.CartItemDocument;
import com.coderhouse.model.response.CartItemResponse;
import com.coderhouse.model.response.CartResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;

public class CartBuilder {
    @Autowired

    public static CartDocument cartDocumentCreate(String address,String email){
            return CartDocument.builder()
                    .items(new ArrayList<>())
                    .address(address)
                    .email(email)
                    .date((new Date(System.currentTimeMillis())).toString())
                    .items(new ArrayList<CartItemDocument>())
                    .build();
    }

    public static CartResponse cartEntityToResponse(CartDocument cart){
        var items= new ArrayList<CartItemResponse>();
        return CartResponse.builder()
                .id(cart.getId())
                .totalAmount(0D)
                .itemsAmount(0)
                .items(items)
                .build();
    }
}
