package com.example.demo1.db.service.api;

import com.example.demo1.db.service.api.request.BuyProductRequest;
import com.example.demo1.db.service.api.response.BuyProductResponse;

public interface ShoppingService {

    BuyProductResponse buyProduct(BuyProductRequest buyProductRequest);
}
