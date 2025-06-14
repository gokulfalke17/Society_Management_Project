package com.society.service;

import java.util.List;

import com.society.entity.Meeting;

public interface IMeetingService {
	public Meeting scheduleMeeting(Meeting meeting);
	public List<Meeting> getUpcomingMeetings();
	public List<Meeting> viewAllMeetings();
	
}
