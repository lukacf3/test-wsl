package com.example.demo1.db.mapper;

import com.example.demo1.domain.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setMerchantId(resultSet.getInt("merchant_id"));
        product.setName(resultSet.getString("name"));
        product.setDescription(resultSet.getString("description"));
        product.setPrice(resultSet.getDouble("price"));
        if(product.getCreatedAt() == null){
            product.setCreatedAt(Timestamp.from(Instant.now()));
        }
        product.setCreatedAt(resultSet.getTimestamp("created_at"));
        product.setAvailable(resultSet.getInt("available"));

        return product;
    }
}
