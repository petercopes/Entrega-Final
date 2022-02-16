package com.coderhouse.service.impl;

import com.coderhouse.model.OrderFactory;
import com.coderhouse.model.database.document.CartItemDocument;
import com.coderhouse.model.response.CartItemResponse;
import com.coderhouse.model.response.OrderResponse;
import com.coderhouse.repository.CartRepository;
import com.coderhouse.repository.OrderRepository;
import com.coderhouse.service.EmailService;
import com.coderhouse.service.OrderService;
import com.coderhouse.service.ProductService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final OrderFactory orderFactory = new OrderFactory();
    private final EmailService emailService;
    @Override
    public OrderResponse createOrder(String cartId) throws Exception {
        System.out.println("service - in createOrder");
        var cart = cartRepository.findById(cartId).orElseThrow(()->new Exception("no cart found with id "+cartId));
        var savedOrder = orderRepository.save(orderFactory.createOrder(cart));
        var itemsResponse = new ArrayList<CartItemResponse>();
        for(CartItemDocument cid: cart.getItems()){
            var product = productService.getByCode(cid.getProductCode());
            itemsResponse.add(CartItemResponse.builder().amount(cid.getAmount()).product(product).total(product.getPrice()*cid.getAmount()).build());
        }
        emailService.sendEmail(savedOrder);
        return OrderResponse.builder().orderNumber(cartId+String.valueOf(System.currentTimeMillis())).items(itemsResponse).date(new Date(System.currentTimeMillis()).toString()).email(cart.getEmail()).address(cart.getAddress()).build();
    }
}
