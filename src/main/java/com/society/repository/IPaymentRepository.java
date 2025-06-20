package com.society.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.society.entity.Payment;

@Repository
public interface IPaymentRepository extends JpaRepository<Payment, Integer> {
	public List<Payment> findByUserUserId(Integer userId);
}
