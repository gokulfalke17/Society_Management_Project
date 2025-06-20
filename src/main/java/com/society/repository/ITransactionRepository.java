package com.society.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import com.society.entity.Transaction;

@RestController
public interface ITransactionRepository extends JpaRepository<Transaction, Integer>{
	public List<Transaction> findByRecordedByUserId(Integer userId);
}
