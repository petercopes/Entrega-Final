package com.coderhouse.controller;

import com.coderhouse.model.response.OrderResponse;
import com.coderhouse.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    @PostMapping("")
    public ResponseEntity<OrderResponse> createOrder(@RequestParam String cartId){
        try {
            System.out.println("controller - in createOrder");
            return new ResponseEntity<>(orderService.createOrder(cartId), HttpStatus.CREATED);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

}
