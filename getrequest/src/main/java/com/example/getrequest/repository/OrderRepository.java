package com.example.getrequest.repository;

import com.example.getrequest.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
}
