package com.society.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.society.entity.MemberAttendance;
import com.society.entity.User;

@Repository
public interface IMemberAttendanceRepository extends JpaRepository<MemberAttendance, Integer> {
    List<MemberAttendance> findByDate(LocalDate date);
    Optional<MemberAttendance> findByUserAndDate(User user, LocalDate date);
}