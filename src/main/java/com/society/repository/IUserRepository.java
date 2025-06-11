package com.society.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.society.entity.User;

public interface IUserRepository extends JpaRepository<User, Integer> {

}
