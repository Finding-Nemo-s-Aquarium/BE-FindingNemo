package org.nimo.aquarium.web.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CartSummary {
    private List<CartItemSummary> items;
    private double total;
}