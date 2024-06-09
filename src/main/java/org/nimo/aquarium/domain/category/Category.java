package org.nimo.aquarium.domain.category;

import jakarta.persistence.*;
import org.nimo.aquarium.domain.item.Item;

import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> subcategories;

    @OneToMany(mappedBy = "category")
    private List<Item> items;
}
