package com.coderhouse.builder;

import com.coderhouse.model.database.document.CartDocument;
import com.coderhouse.model.database.document.OrderDocument;

import lombok.RequiredArgsConstructor;

import java.util.Date;
@RequiredArgsConstructor

public class OrderBuilder {
    public static OrderDocument createOrder(CartDocument cart){
        return OrderDocument.builder()
                .address(cart.getAddress())
                .status("Generada")
                .date((new Date(System.currentTimeMillis())).toString())
                .email(cart.getEmail())
                .items(cart.getItems())
                .build();
    }
}
