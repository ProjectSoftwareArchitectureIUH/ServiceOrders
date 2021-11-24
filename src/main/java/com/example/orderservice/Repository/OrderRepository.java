package com.example.orderservice.Repository;

import com.example.orderservice.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author MPHuy on 23/11/2021
 */
public interface OrderRepository extends JpaRepository<Orders,Long> {
}
