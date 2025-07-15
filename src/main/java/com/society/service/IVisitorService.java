package com.society.service;

import java.util.List;

import com.society.entity.Visitor;

public interface IVisitorService {
	
	public Visitor addVisitor(String userName, Visitor visitor);
	public Visitor checkoutVisitor(Integer visitorId);
	public List<Visitor> getCurrentVisitors();
	public List<Visitor> getVisitorByMember(Integer memberId);
	
	public List<Visitor> getVisitorHistory();
}
