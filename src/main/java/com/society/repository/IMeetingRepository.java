package com.society.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.society.entity.Meeting;

@Repository
public interface IMeetingRepository extends JpaRepository<Meeting, Integer> {

	public List<Meeting> findByScheduledAtAfter(LocalDateTime time); 
}
