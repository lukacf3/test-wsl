package com.example.demo1.db.service.impl;

import com.example.demo1.db.repository.CustomerRepository;
import com.example.demo1.db.service.api.CustomerService;
import com.example.demo1.domain.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository= customerRepository;
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.getAll();
    }

    @Override
    public Customer get(int id) {
        return customerRepository.get(id);
    }

    @Override
    public Integer add(Customer customer) {
        return customerRepository.add(customer);
    }

    @Override
    public void delete(int id) {
        customerRepository.delete(id);
    }
}
