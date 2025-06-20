package com.society.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.society.entity.Payment;
import com.society.entity.Transaction;
import com.society.service.IFinanceService;

@Controller
public class FinanceController {

	@Autowired
	private IFinanceService financeService;

// ---- Payment APIs ----

	@PostMapping("/payment/{userId}")
	public ResponseEntity<Payment> makePayment(@PathVariable("userId") Integer userId, @RequestBody Payment payment) {
		return ResponseEntity.ok(financeService.savePayment(userId, payment));
	}

	@GetMapping("/payments")
	public String getAllPayments(Model model) {
		List<Payment> memberPayments = financeService.getAllPayments();
		model.addAttribute("memberPayments", memberPayments);
		return "member-payments";
	}

	@GetMapping("/payments/{userId}")
	public ResponseEntity<List<Payment>> getUserPayments(@PathVariable("userId") Integer userId) {
		return ResponseEntity.ok(financeService.getUserPayments(userId));
	}

	
	// ---- Transaction APIs ----
	
	/*
	@PostMapping("/transaction/{secretaryId}")
	public ResponseEntity<Transaction> saveTransaction(@PathVariable("secretaryId") Integer secretaryId,
			@RequestBody Transaction transaction) {
		return ResponseEntity.ok(financeService.saveTransaction(secretaryId, transaction));
	}
	
	@GetMapping("/transaction/form")
	public String showTransactionForm(Model model) {
	    Transaction transaction = new Transaction();
	    model.addAttribute("transaction", transaction);
	    model.addAttribute("secretaryId", 1);
	    return "add-transaction";
	}
	
	@PostMapping("/transaction/form/save")
	public String saveTransactionFromForm(@RequestParam("secretaryId") Integer secretaryId,
	                                      @ModelAttribute Transaction transaction) {
	    financeService.saveTransaction(secretaryId, transaction);
	    return "redirect:/transactions/" + secretaryId; 
	}
	
	@GetMapping("/transactions")
	public ResponseEntity<List<Transaction>> getAllTransactions() {
		return ResponseEntity.ok(financeService.getAllTransactions());
	}
	
	 @GetMapping("/transactions/{secretaryId}")
	    public String showSecretaryTransactions(@PathVariable("secretaryId") Integer secretaryId, Model model) {
	        List<Transaction> transactions = financeService.getSecretaryTransactions(secretaryId);
	        model.addAttribute("transactions", transactions);
	        return "transactions"; 
	    }*/

	
	
	@GetMapping("/transactions/form/{secretaryId}")
	public String showTransactionForm(@PathVariable("secretaryId") Integer secretaryId, Model model) {
		model.addAttribute("transaction", new Transaction());
		model.addAttribute("secretaryId", secretaryId);
		return "add-transaction";
	}

	@PostMapping("/transactions/save")
	public String saveTransaction(@RequestParam("secretaryId") Integer secretaryId,
			@ModelAttribute Transaction transaction) {
		financeService.saveTransaction(secretaryId, transaction);
		return "redirect:/transactions";
	}
	
	
	
	/*@PostMapping("/transaction/{secretaryId}")
	public ResponseEntity<Transaction> saveTransaction(@PathVariable("secretaryId") Integer secretaryId,
			@RequestBody Transaction transaction) {
		return ResponseEntity.ok(financeService.saveTransaction(secretaryId, transaction));
	}*/

	@GetMapping("/transactions")
	public String showAllTransactions(Model model) {
		List<Transaction> transactions = financeService.getAllTransactions();
		model.addAttribute("transactions", transactions);
		return "transactions";
	}

	/*@GetMapping("/transactions/{secretaryId}")
	public String showSecretaryTransactions(@PathVariable("secretaryId") Integer secretaryId, Model model) {
	    List<Transaction> transactions = financeService.getSecretaryTransactions(secretaryId);
	    model.addAttribute("transactions", transactions);
	    return "transactions";
	}*/

	
}