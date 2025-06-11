package com.society.service;

import java.util.List;

import com.society.entity.User;

public interface IUserService {
	public User saveUser(User user);
	public User getUserById(Integer id);
	public void deleteUserById(Integer id);
    public List<String> getUserMemberByUserId(Integer id);
	public List<User> findAll();
		
}
