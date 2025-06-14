package com.society.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.society.entity.Admin;
import com.society.entity.User;
import com.society.entity.User.Role;
import com.society.repository.IAdminRepository;
import com.society.repository.IMemberRepository;

@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private IAdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private IMemberRepository memberRepository;

    @Override
    public Admin adminLogin(String email, String role, String password) {
        Admin admin = adminRepository.adminLogin(email, role);
        if (admin != null && passwordEncoder.matches(password, admin.getPassword())) {
        	System.err.println(admin);
            return admin;
        }
        return null;
    }
    
    @Override
    public User memberLogin(String email, User.Role role, String password) {
        Optional<User> memberOpt = memberRepository.memberLogin(email, role);
        if (!memberOpt.isPresent()) {
            return null;
        }
        
        User member = memberOpt.get();
        
        boolean matches = passwordEncoder.matches(password, member.getPassword());
        if (matches) {
            return member;
        } else {
            return null;
        }
    }

	@Override
	public User secretaryLogin(String email, Role role, String password) {
		Optional<User> secretary = adminRepository.secretaryLogin(email, role);
        if (!secretary.isPresent()) {
            return null;
        }
        
        User member = secretary.get();
        
        boolean matches = passwordEncoder.matches(password, member.getPassword());
        if (matches) {
            return member;
        } else {
            return null;
        }
	}

	@Override
	public User securityLogin(String email, Role role, String password) {
		Optional<User> security = adminRepository.securityLogin(email, role);
        if (!security.isPresent()) {
            return null;
        }
        
        User member = security.get();
        
        boolean matches = passwordEncoder.matches(password, member.getPassword());
        if (matches) {
            return member;
        } else {
            return null;
        }
	}

	
	@Override
	public User accountantLogin(String email, Role role, String password) {
	    Optional<User> optionalUser = adminRepository.securityLogin(email, role);
	    if (!optionalUser.isPresent()) {
	        return null;
	    }

	    User accountant = optionalUser.get();
	    boolean matches = passwordEncoder.matches(password, accountant.getPassword());
	    return matches ? accountant : null;
	}


}
