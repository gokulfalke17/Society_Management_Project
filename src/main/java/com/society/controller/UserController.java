package com.society.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.society.entity.Member;
import com.society.entity.User;
import com.society.service.IUserService;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String showHomePage() {
        return "home";
    }

    
    @GetMapping("/user-list")
    public String userList(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/add-user")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    
    
    @PostMapping("/save-user")
    public String saveUser(@ModelAttribute("user") User user,
                           @RequestParam(name = "memberNames", required = false) List<String> memberNames,
                           @RequestParam(name = "memberAges", required = false) List<Integer> memberAges) {

        if (user.getPassword() != null && !user.getPassword().startsWith("$2a$")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        if (user.getRole() == User.Role.MEMBER && memberNames != null && memberAges != null) {
            List<Member> members = new ArrayList<>();
            for (int i = 0; i < memberNames.size(); i++) {
                String name = memberNames.get(i);
                Integer age = memberAges.get(i);
                if (name != null && !name.trim().isEmpty()) {
                    members.add(new Member(null, name.trim(), age, user));
                }
            }
            user.setMembers(members);
            user.setNumberOfMembers(members.size());
        } else {
            user.setMembers(new ArrayList<>());
            user.setNumberOfMembers(null);
        }

        userService.saveUser(user);
        return "redirect:/user-list";
    }

    @GetMapping("/update-user/{userId}")
    public String showUpdateUserForm(@PathVariable("userId") Integer userId, Model model) {
        User user = userService.getUserById(userId);
        if (user == null) return "redirect:/user-list";

        model.addAttribute("user", user);

        List<String> memberNames = new ArrayList<>();
        if (user.getMembers() != null) {
            for (Member m : user.getMembers()) {
                memberNames.add(m.getName());
            }
        }
        model.addAttribute("memberNames", memberNames);

        return "update-user";
    }

    
    @PostMapping("/update-user")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam(name = "memberNames", required = false) List<String> memberNames,
                             @RequestParam(name = "memberAges", required = false) List<Integer> memberAges) {

        if (user.getPassword() != null && !user.getPassword().startsWith("$2a$")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else if (user.getPassword() == null || user.getPassword().isEmpty()) {
            User existingUser = userService.getUserById(user.getUserId());
            user.setPassword(existingUser.getPassword());
        }

        if (user.getRole() == User.Role.MEMBER && memberNames != null && memberAges != null) {
            List<Member> members = new ArrayList<>();
            for (int i = 0; i < memberNames.size(); i++) {
                String name = memberNames.get(i);
                Integer age = memberAges.get(i);
                if (name != null && !name.trim().isEmpty()) {
                    members.add(new Member(null, name.trim(), age, user));
                }
            }
            user.setMembers(members);
            user.setNumberOfMembers(members.size());
        } else {
            user.setMembers(new ArrayList<>());
            user.setNumberOfMembers(null);
        }

        userService.saveUser(user);
        return "redirect:/user-list";
    }

    @GetMapping("/delete-user/{userId}")
    public String deleteUser(@PathVariable("userId") Integer userId) {
        userService.deleteUserById(userId);
        return "redirect:/user-list";
    }

    
    @GetMapping("/user-member/{userId}/members")
    @ResponseBody
    public List<Map<String, Object>> getUserMembers(@PathVariable("userId") Integer userId) {
        return userService.getUserMembersWithAge(userId);
    }
    
}
