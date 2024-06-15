package org.nimo.aquarium.web.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CartItemSummary {
    private String itemName;
    private int count;
    private double total;
}