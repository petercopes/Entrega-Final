package com.coderhouse.service.impl;

import com.coderhouse.builder.CartBuilder;
import com.coderhouse.builder.CartItemBuilder;
import com.coderhouse.model.CartFactory;
import com.coderhouse.model.CartItemFactory;
import com.coderhouse.model.ProductFactory;
import com.coderhouse.model.database.document.CartItemDocument;
import com.coderhouse.model.request.CartItemRequest;
import com.coderhouse.model.response.CartItemResponse;
import com.coderhouse.model.response.CartResponse;
import com.coderhouse.repository.CartItemRepository;
import com.coderhouse.repository.CartRepository;
import com.coderhouse.service.CartService;
import com.coderhouse.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartItemFactory cartItemFactory = new CartItemFactory();
    private final CartFactory cartFactory = new CartFactory();
    private final Logger  logger= LogManager.getLogger(CartServiceImpl.class);
    private final CartRepository cartRepository;
    private final ProductService productService;

    @Override
    public CartResponse createCart(String address,String email) throws Exception{
        System.out.println("en createcart");
        logger.info("in createCart");
        var cart = cartFactory.createCart(address, email);
        logger.info(cart.toString());
        System.out.println(cart);

        var cartSaved = cartRepository.save(cart);
        logger.info(cartSaved.toString());
        return cartFactory.createCartResponse(cartSaved);

    }
    @Override
    public CartItemResponse addProductToCart(CartItemRequest request, String id)throws Exception{
        if(request==null){
            throw new Exception("Empty request");
        }
        if(id==null){
            throw new Exception("No cart id provided");
        }
        var cart = cartRepository.findById(id).orElseThrow(()-> new Exception("no cart found under that id"));
        var cartItems = cart.getItems();
        var found = false;
        for(CartItemDocument cd : cartItems){
            if(cd.getProductCode().equals(request.getProductCode())) {
                found = true;
                cd.setAmount(request.getAmount());
            }
        }
        if(!found){
           cart.getItems().add(cartItemFactory.createCartItem(request));
        }
        cartRepository.save(cart);
        var product = productService.getByCode(request.getProductCode());
        return CartItemResponse.builder().product(product).amount(request.getAmount()).total(product.getPrice()* request.getAmount()).build();
    }

    @Override
    public CartResponse getCart(String id) throws Exception {
        //return cartItemFactory.createCartResponse(cartItemRepository.findAll());
        if(id==null){
            throw new Exception("No id provided");
        }
        var cart = cartRepository.findById(id).orElseThrow(()->new Exception("No cart found with id "+id));
        var itemsResponse = new ArrayList<CartItemResponse>();
        var totalAmount=0D;
        var itemsAmount=0;
        for(CartItemDocument cid : cart.getItems()){
            var product = productService.getByCode(cid.getProductCode());
            totalAmount+=product.getPrice()*cid.getAmount();
            itemsAmount+=cid.getAmount();
            itemsResponse.add(CartItemResponse.builder().product(product).amount(cid.getAmount()).total(product.getPrice()*cid.getAmount()).build());
        }
        var cartResponse = CartResponse.builder().items(itemsResponse).itemsAmount(itemsAmount).totalAmount(totalAmount).id(cart.getId()).build();
        return cartResponse;
    }

    @Override
    public CartItemResponse getCartItem(String code,String id) throws Exception {
        if(code==null){
            throw new Exception("no product code provided");
        }
        if(id==null){
            throw new Exception("no cart id provided");
        }
        var cart = cartRepository.findById(id).orElseThrow(()->new Exception("no cart found with id "+id));
        var itemOnCart = cart.getItems().stream().filter(item->item.getProductCode().equals(code)).findFirst().orElseThrow(()->new Exception("no item found on cart under product code "+code));
        var product = productService.getByCode(itemOnCart.getProductCode());
        if(product==null){
            throw new Exception("no product with that code found on database");
        }
        return CartItemResponse.builder().total(itemOnCart.getAmount()*product.getPrice()).product(product).amount(itemOnCart.getAmount()).build();
    }
    @Override
    public CartItemResponse updateCartItem(String code, CartItemRequest request,String id) throws Exception {
        if(code==null){
            throw new Exception("no product code provided");
        }
        if(id==null){
            throw new Exception("no cart id provided");
        }
        var product = productService.getByCode(code);
        if(product==null){
            throw new Exception("no product with that code found on database");
        }
        var found= false;
        var cart = cartRepository.findById(id).orElseThrow(()->new Exception("no cart found with id "+id));
        for (CartItemDocument cid : cart.getItems()){
            if(request.getProductCode().equals(cid.getProductCode())){
                cid.setAmount(request.getAmount());
                found=true;
            }
        }
        if(!found){
            throw new Exception("no item found on cart with product code "+request.getProductCode());
        }
        cartRepository.save(cart);

        return CartItemResponse.builder().total(request.getAmount()*product.getPrice()).product(product).amount(request.getAmount()).build();
    }
    @Override
    public CartItemResponse deleteItemFromCart(String code,String id) throws Exception{
        if(code==null){
            throw new Exception("no product code provided");
        }
        if(id==null){
            throw new Exception("no cart id provided");
        }
        var product = productService.getByCode(code);
        if(product==null){
            throw new Exception("no product with that code found on database");
        }
        CartItemDocument cartItemDoc= null;

        var cart = cartRepository.findById(id).orElseThrow(()->new Exception("no cart found with id "+id));
        for (CartItemDocument cid : cart.getItems()){
            if(code.equals(cid.getProductCode())){
                cartItemDoc=cid;
            }
        }
        if(cartItemDoc!=null){
            cart.getItems().remove(cartItemDoc);
        }
        cartRepository.save(cart);
        return CartItemResponse.builder().total(cartItemDoc.getAmount()*product.getPrice()).product(product).amount(cartItemDoc.getAmount()).build();
    }
}
