package com.society.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.entity.User;
import com.society.entity.Visitor;
import com.society.repository.IUserRepository;
import com.society.repository.IVisitoryRepositor;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class VisitorServiceImpl implements IVisitorService {

	@Autowired
	private IVisitoryRepositor visitoryRepositor;

	@Autowired
	private IUserRepository userRepository;

	@Override
	public Visitor addVisitor(String userName, Visitor visitor) {
		User member = userRepository.findMemberByName(userName)
				.orElseThrow(() -> new IllegalArgumentException("Member Not Found..."));

		visitor.setMember(member);
		visitor.setCheckInTime(LocalDateTime.now());
		visitor.setCheckOutTime(null);
		return visitoryRepositor.save(visitor);
	}

	@Override
	public Visitor checkoutVisitor(Integer visitorId) {

		Visitor visitor = visitoryRepositor.findById(visitorId)
				.orElseThrow(() -> new IllegalArgumentException("Visitor ID " + visitorId + " Not Found.!"));

		if (visitor.getCheckOutTime() != null) {
			throw new IllegalStateException("Visitor already checked-out");
		}

		visitor.setCheckOutTime(LocalDateTime.now());
		return visitoryRepositor.save(visitor);
	}

	@Override
	public List<Visitor> getCurrentVisitors() {
		return visitoryRepositor.findCurrentVisitors();
	}

	@Override
	public List<Visitor> getVisitorByMember(Integer memberId) {
		return visitoryRepositor.findVisitorsByMemberId(memberId);
	}

	@Override
	public List<Visitor> getVisitorHistory() {
		return visitoryRepositor.findVisitorHistory();
	}

}
