package com.example.demo1.db.repository;

import com.example.demo1.db.mapper.BoughtProductRowMapper;
import com.example.demo1.domain.BoughtProduct;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BoughtProductRepository {
    private final JdbcTemplate jdbcTemplate;
    private final BoughtProductRowMapper boughtProductRowMapper = new BoughtProductRowMapper();
    public BoughtProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }
    public void add(BoughtProduct boughtProduct){
        final String sql = "Insert into bought_product(product_id, customer_id, quantity, bought_at) values (?,?,?,?)";
        jdbcTemplate.update(sql,boughtProduct.getProductId(),boughtProduct.getCustomerId(), boughtProduct.getQuantity(), boughtProduct.getBought_at());
    }
    public List<BoughtProduct> getAllByCustomerId(int customerId){
        final String sql = "select * from bought_product where customer_id = "+customerId;
        return jdbcTemplate.query(sql,boughtProductRowMapper);
    }
}
