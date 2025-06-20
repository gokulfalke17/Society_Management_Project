package com.society.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.entity.MemberAttendance;
import com.society.entity.User;
import com.society.repository.IMemberAttendanceRepository;
import com.society.repository.IUserRepository;

@Service
public class AttendanceServiceImpl implements IAttendanceService {

	@Autowired
	private IMemberAttendanceRepository attendanceRepository;

	@Autowired
	private IUserRepository userRepository;

	@Override
	public void markCheckIn(Integer userId) {
		User user = userRepository.findById(userId).orElseThrow();
		LocalDate today = LocalDate.now();

		if (attendanceRepository.findByUserAndDate(user, today).isEmpty()) {
			MemberAttendance attendance = new MemberAttendance();
			attendance.setUser(user);
			attendance.setDate(today);
			attendance.setCheckInTime(LocalDateTime.now());
			attendanceRepository.save(attendance);
		}
	}

	@Override
	public void markCheckOut(Integer userId) {
		User user = userRepository.findById(userId).orElseThrow();
		LocalDate today = LocalDate.now();
		MemberAttendance attendance = attendanceRepository.findByUserAndDate(user, today).orElseThrow();
		attendance.setCheckOutTime(LocalDateTime.now());
		attendanceRepository.save(attendance);
	}

	@Override
	public List<Map<String, Object>> getTodayAttendance() {
	    List<MemberAttendance> records = attendanceRepository.findByDate(LocalDate.now());

	    return records.stream().map(a -> {
	        Map<String, Object> data = new LinkedHashMap<>();
	        data.put("id", a.getId());
	        data.put("userId", a.getUser().getUserId());
	        data.put("memberName", a.getUser().getName());
	        data.put("date", a.getDate());
	        data.put("checkInTime", a.getCheckInTime());
	        data.put("checkOutTime", a.getCheckOutTime());
	        return data;
	    }).collect(Collectors.toList());
	}

}
