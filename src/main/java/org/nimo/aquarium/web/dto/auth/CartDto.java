package org.nimo.aquarium.web.dto.auth;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartDto {
    private List<CartItemDto> items;
    private double total;

    public CartDto() {
    }

    public CartDto(List<CartItemDto> items, double total) {
        this.items = items;
        this.total = total;
    }

    // Getters and Setters
    public List<CartItemDto> getItems() {
        return items;
    }

    public void setItems(List<CartItemDto> items) {
        this.items = items;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "CartDto{" +
                "items=" + items +
                ", total=" + total +
                '}';
    }
}