package com.coderhouse.builder;

import com.coderhouse.model.database.document.UserDocument;

import com.coderhouse.model.request.UserRequest;
import com.coderhouse.model.response.UserResponse;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;

public class UserBuilder {
    public static UserDocument requestToEntityUser(UserRequest request){
        return UserDocument.builder()
                .name(request.getName())
                .email(request.getEmail())
                .hashedPass(BCrypt.hashpw(request.getPassword(),BCrypt.gensalt()))
                .phone(request.getPhone())
                .build();
    }

    public static UserResponse entityToResponseCreate(UserDocument entity) {
        return UserResponse.builder()
                .name(entity.getName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .build();
    }
    public static List<UserResponse> entityListToResponse(List<UserDocument> users){
        List<UserResponse> responseList = new ArrayList<>();
        for (UserDocument user : users) {
            responseList.add(entityToResponseCreate(user));
        }
        return responseList;
    }

}
