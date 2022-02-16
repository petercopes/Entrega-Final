package com.coderhouse.model.database.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDocument {
    @Id
    private String id;
    private String email;
    private String date;
    private String status;
    private String address;
    private List<CartItemDocument> items;
}
