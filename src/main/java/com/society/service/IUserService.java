package com.society.service;

import java.util.List;
import java.util.Map;

import com.society.entity.User;

public interface IUserService {
	public User saveUser(User user);
	public User getUserById(Integer id);
	public void deleteUserById(Integer id);
	List<Map<String, Object>> getUserMembersWithAge(Integer userId);
	public List<User> findAll();
	
	public List<User> getAllUsers(User.Role role);
	public List<User> getAllMembers();
		
}
