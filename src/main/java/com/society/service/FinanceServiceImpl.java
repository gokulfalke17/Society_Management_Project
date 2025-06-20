package com.society.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.entity.Payment;
import com.society.entity.Transaction;
import com.society.entity.User;
import com.society.repository.IPaymentRepository;
import com.society.repository.ITransactionRepository;
import com.society.repository.IUserRepository;

@Service
public class FinanceServiceImpl implements IFinanceService {

	@Autowired
    private IPaymentRepository paymentRepository;

    @Autowired
    private ITransactionRepository transactionRepository;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public Payment savePayment(int userId, Payment payment) {
        User user = userRepository.findById(userId).orElseThrow();
        payment.setUser(user);
        payment.setPaymentDate(LocalDate.now());
        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public List<Payment> getUserPayments(int userId) {
        return paymentRepository.findByUserUserId(userId);
    }

    @Override
    public Transaction saveTransaction(int secretaryId, Transaction transaction) {
        User secretary = userRepository.findById(secretaryId).orElseThrow();
        transaction.setRecordedBy(secretary);
        transaction.setTransactionDate(LocalDate.now());
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getSecretaryTransactions(int secretaryId) {
        return transactionRepository.findByRecordedByUserId(secretaryId);
    }
}
