package com.example.demo1.db.service.api;

import com.example.demo1.domain.Customer;
import org.springframework.lang.Nullable;

import java.util.List;

public interface CustomerService {
    List<Customer> getCustomers();

    @Nullable
    Customer get(int id);

    @Nullable
    Integer add(Customer customer); //returns generated id

    void delete(int id);

}
