package com.coderhouse.model.database.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("cart")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDocument {
    @Id
    private String id;
    private String email;
    private String date;
    private String address;
    private List<CartItemDocument> items;

}
