package com.coderhouse.model.database.document;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document("product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public  class ProductDocument {

    @Id
    private String id;
    private String name;
    private String description;
    private Double price;
    private String category;
    private String code;

}
