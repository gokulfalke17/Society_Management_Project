package com.society.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.society.entity.Member;
import com.society.entity.User;


@Repository
public interface IMemberRepository extends JpaRepository<Member, Integer> {

	@Query("SELECT m.name FROM Member m WHERE m.user.userId = :id")
	public List<String> getMemberNames(@Param("id") Integer id);
	
	@Query("FROM User u WHERE u.email = :email AND u.role = :role")
	Optional<User> memberLogin(@Param("email") String email, @Param("role") User.Role role);

}