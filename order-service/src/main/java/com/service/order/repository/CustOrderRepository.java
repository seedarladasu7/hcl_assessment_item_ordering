package com.service.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.order.entity.CustOrder;

@Repository
public interface CustOrderRepository extends JpaRepository<CustOrder, Integer> {

}
