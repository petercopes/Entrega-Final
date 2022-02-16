package com.coderhouse.controller;

import com.coderhouse.model.request.CartItemRequest;

import com.coderhouse.model.response.CartItemResponse;
import com.coderhouse.model.response.CartResponse;
import com.coderhouse.service.CartService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Data
@RestController
@RequestMapping("/carrito")
public class CartController {
    private final CartService cartService;
    @PostMapping("")
    public ResponseEntity<CartResponse> createCart(@RequestParam String address, @RequestParam String email){
        try {

            return new ResponseEntity<>(cartService.createCart(address,email), HttpStatus.CREATED);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @PostMapping("/{id}/add")
    public ResponseEntity<CartItemResponse> addProductToCart(
            @Validated @RequestBody CartItemRequest request,@PathVariable String id) {
        try{
            return new ResponseEntity<>(cartService.addProductToCart(request,id),HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<CartResponse> getCart(@PathVariable String id){
        try{
            return new ResponseEntity<>(cartService.getCart(id),HttpStatus.OK);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }

    @GetMapping("/{id}/{code}")
    public ResponseEntity<CartItemResponse> getCartItem(@PathVariable String code,@PathVariable String id){
        try {
            return new ResponseEntity<>(cartService.getCartItem(code,id),HttpStatus.OK);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }
    @PutMapping("/{id}/{code}")
    public ResponseEntity<CartItemResponse> updateCartItem(@PathVariable String code,@RequestBody CartItemRequest request,@PathVariable String id){
        try {
           return new ResponseEntity<>(cartService.updateCartItem(code,request,id),HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
    @DeleteMapping("/{id}/{code}")
    public ResponseEntity<CartItemResponse> deleteItem(@PathVariable String code, @PathVariable String id){
        try {
            return new ResponseEntity<>(cartService.deleteItemFromCart(code,id),HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }

    }

}
