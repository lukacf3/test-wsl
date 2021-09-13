package com.example.demo1.db.repository;

import com.example.demo1.domain.CustomerAccount;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CustomerAccountRepository {
    private JdbcTemplate jdbcTemplate;

    public CustomerAccountRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void add(CustomerAccount customerAccount){
        final String sql = "INSERT INTO customer_account(customer_id, money) values (?,?)";
        jdbcTemplate.update(sql, customerAccount.getCustomerId(), customerAccount.getMoney());
    }
    @Nullable
    public Double getMoney(int customerId){
        final String sql = "SELECT money from customer_account where customer_id = "+customerId;
        try {
            return jdbcTemplate.queryForObject(sql,Double.class);
        }catch (DataAccessException e){
            return null;
        }
    }

    public void SetMoney(int customerId, double money){
        final String sql = "UPDATE customer_account SET money = ? WHERE customer_id = ?";
        jdbcTemplate.update(sql, money,customerId);
    }
}
