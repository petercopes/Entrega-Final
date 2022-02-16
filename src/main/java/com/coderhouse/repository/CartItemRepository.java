package com.coderhouse.repository;

import com.coderhouse.model.database.document.CartItemDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends MongoRepository<CartItemDocument,String> {

    public CartItemDocument findCartItemDocumentByProductCode(String code);
    public List<CartItemDocument> findAll();

}
