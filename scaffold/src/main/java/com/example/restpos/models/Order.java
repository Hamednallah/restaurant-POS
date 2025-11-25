package com.example.restpos.models;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<OrderItem> items;
    private double discount;
    private boolean vatApplied;
    private static final double VAT_RATE = 0.15; // 15%

    public Order() {
        this.items = new ArrayList<>();
        this.discount = 0;
        this.vatApplied = false;
    }

    public void addItem(Product product) {
        for (OrderItem item : items) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
        items.add(new OrderItem(product, 1));
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public double getSubtotal() {
        double subtotal = 0;
        for (OrderItem item : items) {
            subtotal += item.getLineTotal();
        }
        return subtotal;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getVat() {
        if (vatApplied) {
            return (getSubtotal() - discount) * VAT_RATE;
        }
        return 0;
    }

    public void setVatApplied(boolean vatApplied) {
        this.vatApplied = vatApplied;
    }

    public double getTotal() {
        return getSubtotal() - discount + getVat();
    }
}