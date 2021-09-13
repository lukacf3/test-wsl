package com.example.demo1.domain;

import org.springframework.lang.Nullable;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

public class BoughtProduct {
    @Nullable
    private int productId;
    @Nullable
    private int customerId;
    @Nullable
    private int quantity;
    @Nullable
    private Timestamp bought_at;

    public BoughtProduct(int productId, int customerId, int quantity) {
        this.productId = productId;
        this.customerId = customerId;
        this.quantity = quantity;
        this.bought_at = Timestamp.from(Instant.now());
    }

    public BoughtProduct(){}

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getBought_at() {
        return bought_at;
    }

    public void setBought_at(Timestamp bought_at) {
        this.bought_at = bought_at;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoughtProduct that = (BoughtProduct) o;
        return productId == that.productId && customerId == that.customerId && quantity == that.quantity && Objects.equals(bought_at, that.bought_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, customerId, quantity, bought_at);
    }
}
