package com.society.service;

import java.util.List;

import com.society.entity.Payment;
import com.society.entity.Transaction;

public interface IFinanceService {
	public Payment savePayment(int userId, Payment payment);
	public List<Payment> getAllPayments();
	public List<Payment> getUserPayments(int userId);
	
	public Transaction saveTransaction(int secretaryId, Transaction transaction);
	public List<Transaction> getAllTransactions();
	public List<Transaction> getSecretaryTransactions(int secretaryId);
}
