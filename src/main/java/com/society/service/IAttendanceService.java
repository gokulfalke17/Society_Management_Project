package com.society.service;

import java.util.List;
import java.util.Map;

public interface IAttendanceService {
	public void markCheckIn(Integer userId);

	public void markCheckOut(Integer userId);

	public List<Map<String, Object>> getTodayAttendance();
}
