package com.society.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.society.entity.Visitor;

@Repository
public interface IVisitoryRepositor extends JpaRepository<Visitor, Integer> {

	@Query("SELECT v FROM Visitor v WHERE v.checkOutTime IS NULL")
	public List<Visitor> findCurrentVisitors();

	@Query("SELECT v FROM Visitor v WHERE v.member.userId = :memberId ORDER BY v.checkInTime DESC")
	public List<Visitor> findVisitorsByMemberId(@Param("memberId") Integer memberId);

	@Query("SELECT v FROM   Visitor v  WHERE  v.checkOutTime IS NOT NULL ORDER  BY v.checkInTime DESC")
	public List<Visitor> findVisitorHistory();
}
