package com.ladharitech.ladhari.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id = UUID.randomUUID();
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String slug;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false,columnDefinition="TEXT")
    private String description;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private int stock;
    @ManyToOne(targetEntity = Categories.class)
    @JoinColumn(nullable = false)
    private Categories categories;
    @Column(nullable = false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private Timestamp createdAt;

    @OneToMany(mappedBy = "products",targetEntity = Images.class,orphanRemoval = true,cascade = CascadeType.PERSIST)
    private List<Images> images;

    @OneToMany(mappedBy = "products",targetEntity = OrdersDetails.class)
    private List<OrdersDetails> ordersDetails;

    public void addImage(Images image) {
        images.add(image);
        image.setProducts(this);
    }

    public void removeImage(Images image) {
        images.remove(image);
        image.setProducts(null);
    }


    public void addOrdersDetail(OrdersDetails ordersDetail) {
        ordersDetails.add(ordersDetail);
        ordersDetail.setProducts(this);
    }

    public void removeOrdersDetail(OrdersDetails ordersDetail) {
        ordersDetails.remove(ordersDetail);
        ordersDetail.setProducts(null);
    }
}
