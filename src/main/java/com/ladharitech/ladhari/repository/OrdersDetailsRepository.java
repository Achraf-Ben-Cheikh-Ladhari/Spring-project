package com.ladharitech.ladhari.repository;

import com.ladharitech.ladhari.entity.OrdersDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrdersDetailsRepository extends JpaRepository<OrdersDetails, UUID> {
}
