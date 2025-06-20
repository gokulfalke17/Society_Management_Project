package com.society.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.society.entity.Member;
import com.society.entity.User;

public interface IUserRepository extends JpaRepository<User, Integer> {
	
	@Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.members m WHERE u.role = :role")
	public List<User> getAllUsersWithMembers(@Param("role") User.Role role);
	public List<User> findByRole(User.Role role);
	
	
}
