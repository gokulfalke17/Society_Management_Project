package com.society.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.society.entity.User;
import com.society.service.IAttendanceService;
import com.society.service.IUserService;

@Controller
public class AttendanceController {

	@Autowired
	private IAttendanceService attendanceService;

	@Autowired
	private IUserService userService;

	@GetMapping("/attendance/checkin-form")
	public String showCheckinForm(Model model) {
		List<User> members = userService.getAllMembers();
		model.addAttribute("members", members);
		return "attendance-checkin";
	}

	@PostMapping("/checkin/{userId}")
	public ResponseEntity<?> checkIn(@PathVariable("userId") Integer userId) {
	    attendanceService.markCheckIn(userId);
	    return ResponseEntity.ok("Checked in");
	}

	@PostMapping("/checkout/{userId}")
	public ResponseEntity<?> checkOut(@PathVariable("userId") Integer userId) {
		attendanceService.markCheckOut(userId);
		return ResponseEntity.ok("Checked out");
	}

	@GetMapping("/today")
	public String showTodayAttendance(Model model) {
	    List<Map<String, Object>> attendanceList = attendanceService.getTodayAttendance();
	    model.addAttribute("attendanceList", attendanceList);
	    return "today-attendance"; // Make sure this is correct HTML file name
	}
}
