package com.example.demo1.db.mapper;

import com.example.demo1.domain.BoughtProduct;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BoughtProductRowMapper implements RowMapper<BoughtProduct> {


    @Override
    public BoughtProduct mapRow(ResultSet resultSet, int i) throws SQLException {
        BoughtProduct boughtProduct = new BoughtProduct();
        boughtProduct.setProductId(resultSet.getInt("product_id"));
        boughtProduct.setCustomerId(resultSet.getInt("customer_id"));
        boughtProduct.setQuantity(resultSet.getInt("quantity"));
        boughtProduct.setBought_at(resultSet.getTimestamp("bought_at"));
        return boughtProduct;
    }
}
