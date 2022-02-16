package com.coderhouse.service;

import com.coderhouse.model.request.UserRequest;
import com.coderhouse.model.response.UserResponse;

import java.util.List;


public interface UserService {
    UserResponse create(UserRequest request) throws Exception;
    UserResponse update(String id, UserRequest request) throws Exception;
    List<UserResponse> searchAll();
    String authenticateUser(String username, String pwd) throws Exception;
}
