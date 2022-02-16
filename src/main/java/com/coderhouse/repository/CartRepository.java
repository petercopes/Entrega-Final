package com.coderhouse.repository;

import com.coderhouse.model.database.document.CartDocument;
import com.coderhouse.model.database.document.ProductDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository  extends MongoRepository<CartDocument, String>{
}
