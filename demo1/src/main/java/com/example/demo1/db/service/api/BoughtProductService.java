package com.example.demo1.db.service.api;

import com.example.demo1.domain.BoughtProduct;

import java.util.List;

public interface BoughtProductService {

    void add(BoughtProduct boughtProduct);

    List<BoughtProduct> getAllByCustomerId(int customerId);
}
