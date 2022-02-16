package com.coderhouse.repository;

import com.coderhouse.model.database.document.CartDocument;
import com.coderhouse.model.database.document.OrderDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository  extends MongoRepository<OrderDocument, String> {
}
