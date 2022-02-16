package com.coderhouse.model;

import com.coderhouse.builder.OrderBuilder;
import com.coderhouse.model.database.document.CartDocument;
import com.coderhouse.model.database.document.OrderDocument;
import lombok.Data;

@Data
public class OrderFactory {

    public OrderDocument createOrder(CartDocument cd){
        return OrderBuilder.createOrder(cd);
    }
}
