package com.ladharitech.ladhari.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id = UUID.randomUUID();
    private String name;
    private String slug;
    @Column(name="category_order")
    private int categoryOrder;
    @ManyToOne(cascade =CascadeType.REMOVE ,targetEntity = Categories.class)
    @JoinColumn()
    private Categories parent;
    @OneToMany(mappedBy = "parent",targetEntity = Categories.class)
    private List<Categories> categories;
    @OneToMany(mappedBy = "categories",targetEntity = Products.class)
    private List<Products>products;
    public void addCategory(Categories category) {
        categories.add(category);
        category.setParent(this);
    }
    public void removeCategory(Categories category) {
        categories.remove(category);
        category.setParent(null);
    }
    public void addProduct(Products product) {
        products.add(product);
        product.setCategories(this);
    }
    public void removeProduct(Products product) {
        products.remove(product);
        product.setCategories(null);
    }
}
