package org.nimo.aquarium.domain.item;

import jakarta.persistence.*;
import lombok.*;
import org.nimo.aquarium.domain.category.Category;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
