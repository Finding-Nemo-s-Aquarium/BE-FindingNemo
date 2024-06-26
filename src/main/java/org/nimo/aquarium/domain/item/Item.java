package org.nimo.aquarium.domain.item;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Item {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    private String name;

    @Getter
    private double price;

    @Setter
    @Getter
    private String imgUrl;

    public void setPrice(double price) {
        this.price = price;
    }
    public void getImgUrl(String imgUrl) { this.imgUrl = imgUrl;}
}
