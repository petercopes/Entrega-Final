package com.coderhouse.service;

import com.coderhouse.model.response.OrderResponse;
import org.springframework.stereotype.Service;


public interface OrderService {
    public OrderResponse createOrder(String cartId) throws Exception;
}
