package com.coderhouse.model.response;

import com.coderhouse.model.database.document.CartItemDocument;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartResponse {
    String id;
    List<CartItemResponse> items;
    Double totalAmount;
    Integer itemsAmount;
}
