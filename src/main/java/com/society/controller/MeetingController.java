package com.society.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.society.entity.Meeting;
import com.society.entity.User;
import com.society.service.IMeetingService;
import com.society.service.IUserService;

@Controller
@RequestMapping("/meetings")
public class MeetingController {

    @Autowired
    private IMeetingService meetingService;
    
    @Autowired
    private IUserService userService;

    
    @GetMapping("/schedule")
    public String scheduleMeetingForm(Model model) {
        model.addAttribute("meeting", new Meeting());
        return "schedule-meeting";
    }

    @PostMapping("/schedule")
    public String scheduleMeeting(Meeting meeting) {
        meetingService.scheduleMeeting(meeting);
        return "redirect:/meetings/upcomming";
    }

    @GetMapping("/upcomming")
    public String viewUpcomingMeetings(Model model) {
        List<Meeting> upcomingMeetings = meetingService.getUpcomingMeetings();
        model.addAttribute("meetings", upcomingMeetings);
        return "upcomming-meetings";
    }

    @GetMapping("/all")
    public String viewAllMeetingsInDashboard(Model model) {
        List<Meeting> allMeetings = meetingService.viewAllMeetings();
        model.addAttribute("allMeetings", allMeetings);
        return "all-meetings"; 
    }
    
    
    @GetMapping("/all-userwise-members")
    public String viewMembers(Model model) {
        List<User> members = userService.getAllUsers(User.Role.MEMBER);
        model.addAttribute("memberUsers", members);
        return "show-members";
    }
}
