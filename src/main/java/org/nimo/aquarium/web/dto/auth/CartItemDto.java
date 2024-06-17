package org.nimo.aquarium.web.dto.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartItemDto {
    private String name;
    private int amount;
    private double price;
    @JsonProperty("img_url")
    private String imgUrl;

    public CartItemDto() {
    }

    public CartItemDto(String name, int amount, double price, String imgUrl) {
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CartItemDto{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}