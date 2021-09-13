package com.example.demo1.db.mapper;

import com.example.demo1.domain.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
        Customer customer = new Customer();
        customer.setId(resultSet.getInt("id"));
        customer.setName(resultSet.getString("name"));
        customer.setSurname(resultSet.getString("surname"));
        customer.setEmail(resultSet.getString("email"));
        customer.setAddress(resultSet.getString("address"));
        customer.setAge(resultSet.getObject("age") == null ? null: resultSet.getInt("age"));
        customer.setPhoneNumber(resultSet.getString("phone_number"));
        return customer;
    }
}
