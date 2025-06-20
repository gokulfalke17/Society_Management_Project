package com.society.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.entity.Member;
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
	public List<Map<String, Object>> getUserMembersWithAge(Integer userId) {
		List<Member> members = memberRepository.findMembersByUserId(userId);
		List<Map<String, Object>> memberList = new ArrayList<>();

		for (Member member : members) {
			Map<String, Object> map = new HashMap<>();
			map.put("name", member.getName());
			map.put("age", member.getAge());
			memberList.add(map);
		}

		return memberList;
	}

	@Override
	public void deleteUserById(Integer id) {
		userRepository.deleteById(id);
	}

	@Override
	public List<User> getAllUsers(User.Role role) {
		return userRepository.getAllUsersWithMembers(role);
	}

	@Override
	public List<User> getAllMembers() {
		return userRepository.findByRole(User.Role.MEMBER);
	}

}
