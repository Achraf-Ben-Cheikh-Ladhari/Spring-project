package com.ladharitech.ladhari.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id= UUID.randomUUID();
    @Column(nullable = false)
    private String name;
    @ManyToOne(targetEntity = Products.class)
    @JoinColumn(nullable = false)
    private Products products;

}
