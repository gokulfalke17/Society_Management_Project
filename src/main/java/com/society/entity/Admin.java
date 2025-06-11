package com.society.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer adminId;
	private String email;
	private String password;
	private String role;	//ADMIN
}
