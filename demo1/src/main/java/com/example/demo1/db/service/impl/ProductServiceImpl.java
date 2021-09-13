package com.example.demo1.db.service.impl;

import com.example.demo1.db.repository.ProductRepository;
import com.example.demo1.db.service.api.ProductService;
import com.example.demo1.db.service.api.request.UpdateProductRequest;
import com.example.demo1.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProduct() {
        return productRepository.getAll();
    }

    @Override
    public Product get(int id) {
        return productRepository.get(id);
    }

    @Override
    public Integer add(Product product) {
        return productRepository.add(product);
    }

    @Override
    public void delete(int id) {
        productRepository.delete(id);
    }

    @Override
    public void update(int id, UpdateProductRequest updateProductRequest) {
        productRepository.update(id, updateProductRequest);
    }

    @Override
    public void updateAvailable(int id, int newAvailable) {
        productRepository.updateAvailable(id,newAvailable);
    }
}
