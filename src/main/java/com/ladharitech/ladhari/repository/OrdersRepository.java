package com.ladharitech.ladhari.repository;

import com.ladharitech.ladhari.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrdersRepository extends JpaRepository<Orders, UUID> {
}
