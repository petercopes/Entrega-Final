package com.coderhouse.service;

import com.coderhouse.model.request.CartItemRequest;
import com.coderhouse.model.response.CartItemResponse;
import com.coderhouse.model.response.CartResponse;

public interface CartService {
    public CartResponse createCart(String address,String email) throws Exception;
    public CartItemResponse addProductToCart(CartItemRequest request,String id) throws Exception;
    public CartResponse getCart(String id) throws Exception;
    public CartItemResponse getCartItem(String code,String id) throws Exception;
    public CartItemResponse updateCartItem(String code, CartItemRequest request,String id) throws Exception;
    public CartItemResponse deleteItemFromCart(String code,String id) throws Exception;
}
