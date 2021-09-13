package com.example.demo1.db.service.api.request;

import java.util.Objects;

public class BuyProductRequest {
    private int productId;
    private int costumerId;
    private int quantity;

    public BuyProductRequest(int productId, int costumerId, int quantity) {
        this.productId = productId;
        this.costumerId = costumerId;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public int getCostumerId() {
        return costumerId;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuyProductRequest that = (BuyProductRequest) o;
        return productId == that.productId && costumerId == that.costumerId && quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, costumerId, quantity);
    }
}
