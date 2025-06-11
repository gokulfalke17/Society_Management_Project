package com.society.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.society.entity.Admin;
import com.society.entity.User;

@Repository
public interface IAdminRepository extends JpaRepository<Admin, Integer> {

	@Query("SELECT a FROM Admin a WHERE a.email = :email AND a.role = :role")
	Admin adminLogin(@Param("email") String email, @Param("role") String role);
	
	@Query("FROM User u WHERE u.email = :email AND u.role = :role")
	Optional<User> secretaryLogin(@Param("email") String email, @Param("role") User.Role role);
	
	@Query("FROM User u WHERE u.email = :email AND u.role = :role")
	Optional<User> securityLogin(@Param("email") String email, @Param("role") User.Role role);

}