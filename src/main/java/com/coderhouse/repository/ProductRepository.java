package com.coderhouse.repository;

import com.coderhouse.model.database.document.ProductDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<ProductDocument, String> {
    List<ProductDocument> findAllByCategory(String category);
    ProductDocument findByCode(String productCode);

}
