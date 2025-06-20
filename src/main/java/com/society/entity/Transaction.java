package com.society.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer transactionId;
	private String title;
	private String description;
	private Double amount;
	private String paymentMode; // UPI, Cash, Cheque
	private String paidTo; // Vendor/Contractor Name
	private LocalDate transactionDate;

	@ManyToOne
	private User recordedBy; // SECRETARY
}
