package com.ladharitech.ladhari.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(OrdersDetails.OrdersDetailsId.class)
public class OrdersDetails {
    @Id
    @ManyToOne(targetEntity = Orders.class)
    @JoinColumn(nullable = false)
    private Orders orders;
    @Id
    @ManyToOne(targetEntity = Products.class)
    @JoinColumn(nullable = false)
    private Products products;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private double price;
    @Data
    @RequiredArgsConstructor
      class OrdersDetailsId implements Serializable {
        private Orders orders;
        private Products products;

    }
}
