package com.coderhouse.model;

import com.coderhouse.builder.UserBuilder;
import com.coderhouse.model.database.document.UserDocument;
import com.coderhouse.model.request.UserRequest;
import lombok.Data;

@Data
public class UserFactory {
    public UserDocument createUser(UserRequest userRequest){
        return UserBuilder.requestToEntityUser(userRequest);
    }
}
