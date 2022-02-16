package com.coderhouse.model.request;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemRequest {
    String productCode;
    Integer amount;
}
