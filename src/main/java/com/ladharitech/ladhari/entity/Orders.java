package com.ladharitech.ladhari.entity;

import com.ladharitech.ladhari.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id = UUID.randomUUID();
    @Column(length = 20,unique = true,nullable = false)
    private String reference;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(nullable = false)
    private User user;
    @Column(length = 45,nullable = false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private Timestamp createdAt;
    @OneToMany(mappedBy = "orders",targetEntity = OrdersDetails.class,orphanRemoval = true,cascade = CascadeType.PERSIST)
    private List<OrdersDetails> ordersDetails;


    public void addOrdersDetail(OrdersDetails ordersDetail) {
        ordersDetails.add(ordersDetail);
        ordersDetail.setOrders(this);
    }

    public void removeOrdersDetail(OrdersDetails ordersDetail) {
        ordersDetails.remove(ordersDetail);
        ordersDetail.setOrders(null);
    }


}
