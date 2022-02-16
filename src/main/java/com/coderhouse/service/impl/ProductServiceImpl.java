package com.coderhouse.service.impl;

import com.coderhouse.builder.ProductBuilder;
import com.coderhouse.model.ProductFactory;
import com.coderhouse.model.request.ProductRequest;
import com.coderhouse.model.response.ProductResponse;
import com.coderhouse.repository.ProductRepository;
import com.coderhouse.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductFactory productFactory = new ProductFactory();

    @Override
    public ProductResponse create(ProductRequest request)throws Exception{
        var entity =
                productFactory.createProduct(request);
        if(entity==null){
            throw new Exception("Error ocurred creating product document");
        }
        var entitySaved = repository.save(entity);
        if(entitySaved==null){
            throw new Exception("Error ocurred saving product document");
        }
        return ProductBuilder.entityToResponseCreate(entitySaved);
    }

    @Override
    public ProductResponse update(String id, ProductRequest request) throws Exception{
        var entity = repository.findByCode(id);
        if(entity==null){
            throw new Exception("Error ocurred while locating product document");
        }
        entity.setCategory(request.getCategory());
        entity.setDescription(request.getDescription());
        entity.setName(request.getName());
        entity.setPrice(entity.getPrice());
        var entitySaved = repository.save(entity);
        if(entitySaved==null){
            throw new Exception("Error ocurred saving product document");
        }
        return ProductBuilder.entityToResponseCreate(entitySaved);
    }

    @Override
    public List<ProductResponse> searchAll() {
        return ProductBuilder.listEntityToResponse(repository.findAll());
    }
    @Override
    public List<ProductResponse> searchByCategory(String category)throws Exception{
        if(category==null){
            throw new Exception("No category provided");
        }
        return ProductBuilder.listEntityToResponse(repository.findAllByCategory(category));
    }

    @Override
    public ProductResponse getByCode(String productCode) throws Exception {
        var productOnDb = this.repository.findByCode(productCode);
        if(productOnDb==null){
            throw new Exception("No product found under product code "+productCode);
        }
        return ProductBuilder.entityToResponseCreate(productOnDb);
    }
    @Override
    public ProductResponse deleteProduct(String id) throws Exception{
        var productOnDb = this.repository.findByCode(id);
        if(productOnDb==null){
            throw new Exception("no product found on db with code "+id);
        }
        try {
            this.repository.deleteById(productOnDb.getId());
            return ProductBuilder.entityToResponseCreate(productOnDb);
        }
        catch (Exception e){
            throw new Exception("error while deleting product on db");
        }
    }
}
