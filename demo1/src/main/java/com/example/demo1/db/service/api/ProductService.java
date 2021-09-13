package com.example.demo1.db.service.api;

import com.example.demo1.db.service.api.request.UpdateProductRequest;
import com.example.demo1.domain.Product;
import org.springframework.lang.Nullable;

import java.util.List;

public interface ProductService {

    List<Product> getProduct();

    @Nullable
    Product get(int id);

    @Nullable
    Integer add(Product product); // returns generated id

    void delete(int id);

    void update(int id, UpdateProductRequest updateProductRequest);

    void updateAvailable(int id, int newAvailable);
}
