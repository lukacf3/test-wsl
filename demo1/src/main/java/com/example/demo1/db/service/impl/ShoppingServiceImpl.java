package com.example.demo1.db.service.impl;

import com.example.demo1.db.service.api.*;
import com.example.demo1.db.service.api.request.BuyProductRequest;
import com.example.demo1.db.service.api.response.BuyProductResponse;
import com.example.demo1.domain.BoughtProduct;
import com.example.demo1.domain.Customer;
import com.example.demo1.domain.Product;
import org.springframework.stereotype.Service;

@Service
public class ShoppingServiceImpl implements ShoppingService {
    private final ProductService productService;
    private final CustomerService customerService;
    private final CustomerAccountService customerAccountService;
    private final BoughtProductService boughtProductService;

    public ShoppingServiceImpl(ProductService productService, CustomerService customerService, CustomerAccountService customerAccountService, BoughtProductService boughtProductService) {
        this.productService = productService;
        this.customerService = customerService;
        this.customerAccountService = customerAccountService;
        this.boughtProductService = boughtProductService;
    }

    @Override
    public BuyProductResponse buyProduct(BuyProductRequest buyProductRequest) {
        int productId= buyProductRequest.getProductId();
        int customerId = buyProductRequest.getCostumerId();

        Product product =productService.get(productId);
        if (product == null){
            return new BuyProductResponse(false, "Product with id: "+productId+ "does not exist");
        }
        Customer customer = customerService.get(customerId);
        if (customer == null){
            return new BuyProductResponse(false, "Customer with id: "+customerId+"does not exist");
        }

        if(product.getAvailable()<buyProductRequest.getQuantity())
            return new BuyProductResponse(false, "Not enough products in stock");

        Double customerMoney = customerAccountService.getMoney(customerId);
        if (customerMoney == null){
            return new BuyProductResponse(false, "Customer with id: "+customerId+"does not have account");
        }else {
            double totalPriceOfRequest = product.getPrice() * buyProductRequest.getQuantity();
            if (customerMoney >= totalPriceOfRequest){
                productService.updateAvailable(productId,product.getAvailable()-buyProductRequest.getQuantity());
                customerAccountService.setMoney(customerId, customerMoney - totalPriceOfRequest);
                boughtProductService.add(new BoughtProduct(productId,customerId,buyProductRequest.getQuantity()));
                return new BuyProductResponse(true);


            }else {
                return new BuyProductResponse(false, "Customer with id: "+customerId+"does not have enough money");
            }
        }
    }
}
