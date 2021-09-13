package com.example.demo1.db.service.api;

import com.example.demo1.domain.CustomerAccount;
import org.springframework.lang.Nullable;

public interface CustomerAccountService {

    void addCustomerAccount(CustomerAccount customerAccount);

    @Nullable
    Double getMoney(int customerId);

    @Nullable
    void setMoney(int customerId, double money);
}
