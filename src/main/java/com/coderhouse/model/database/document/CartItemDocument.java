package com.coderhouse.model.database.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemDocument {
    @Id
    private String id;
    private String productCode;
    private Integer amount;
}
