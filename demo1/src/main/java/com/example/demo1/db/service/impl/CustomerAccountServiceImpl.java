package com.example.demo1.db.service.impl;

import com.example.demo1.db.repository.CustomerAccountRepository;
import com.example.demo1.db.service.api.CustomerAccountService;
import com.example.demo1.domain.CustomerAccount;
import org.springframework.stereotype.Service;

@Service
public class CustomerAccountServiceImpl implements CustomerAccountService {

    private final CustomerAccountRepository customerAccountRepository;

    public CustomerAccountServiceImpl(CustomerAccountRepository customerAccountRepository) {
        this.customerAccountRepository = customerAccountRepository;
    }

    @Override
    public void addCustomerAccount(CustomerAccount customerAccount) {
        customerAccountRepository.add(customerAccount);
    }

    @Override
    public Double getMoney(int customerId) {
        return customerAccountRepository.getMoney(customerId);
    }

    @Override
    public void setMoney(int customerId, double money) {
        customerAccountRepository.SetMoney(customerId,money);
    }
}
