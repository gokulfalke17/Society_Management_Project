package com.society.service;

import com.society.entity.Admin;
import com.society.entity.User;
import com.society.entity.User.Role;

public interface IAdminService {
	public Admin adminLogin(String email, String role, String password);

	public User memberLogin(String email, Role role, String password);
	
	public User secretaryLogin(String email, Role role, String password);
	
	public User securityLogin(String email, Role role, String password);
}
