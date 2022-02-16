package com.coderhouse.model.database.document;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


@Document("user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDocument implements Serializable {
    @Id
    private String id;
    private String name;
    private String phone;
    private String email;
    private String hashedPass;
}
