package com.society.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.society.entity.Meeting;
import com.society.repository.IMeetingRepository;

@Service
public class MeetingServiceImpl implements IMeetingService {

	@Autowired
	private IMeetingRepository meetingRepository;

	@Override
	public Meeting scheduleMeeting(Meeting meeting) {
		return meetingRepository.save(meeting);
	}

	
	@Override
    public List<Meeting> getUpcomingMeetings() {
        return meetingRepository.findByScheduledAtAfter(LocalDateTime.now());
    }


	@Override
	public List<Meeting> viewAllMeetings() {
		return meetingRepository.findAll();
	}

}
