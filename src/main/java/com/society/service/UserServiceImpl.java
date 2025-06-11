package com.society.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.entity.User;
import com.society.repository.IMemberRepository;
import com.society.repository.IUserRepository;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;
    
    @Autowired
    private IMemberRepository memberRepository;
    
    

    @Override
    public User saveUser(User user) {

        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }
    
    @Override
    public List<String> getUserMemberByUserId(Integer id) {
        return memberRepository.getMemberNames(id);
    }

    @Override
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }

}
