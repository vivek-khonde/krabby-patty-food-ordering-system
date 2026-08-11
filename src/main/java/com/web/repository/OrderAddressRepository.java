package com.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.model.OrderAddress;

public interface OrderAddressRepository extends JpaRepository<OrderAddress, Integer> {

}
