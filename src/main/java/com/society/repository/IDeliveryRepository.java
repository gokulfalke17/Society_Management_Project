package com.society.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.society.entity.Delivery;

@Repository
public interface IDeliveryRepository extends JpaRepository<Delivery, Integer> {
	
}