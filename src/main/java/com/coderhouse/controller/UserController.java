package com.coderhouse.controller;

import com.coderhouse.model.request.UserRequest;
import com.coderhouse.model.response.UserResponse;
import com.coderhouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam("email") String email, @RequestParam("password") String pwd) {
        try{
            return new ResponseEntity<String>(userService.authenticateUser(email, pwd), HttpStatus.OK);
        }
        catch (Exception e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getUsers(){
       return new ResponseEntity<>(this.userService.searchAll(),HttpStatus.OK);
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable String id,@RequestBody UserRequest request){
        try {
            return new ResponseEntity<>(this.userService.update(id,request),HttpStatus.ACCEPTED);
        }catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
    @PostMapping("/user")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request){
        try {
            return new ResponseEntity<>(this.userService.create(request),HttpStatus.CREATED);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
}
