package com.society.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.society.entity.Admin;
import com.society.entity.User;
import com.society.service.IAdminService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

	@Autowired
	private IAdminService adminService;

	

//------------------------------------------Admin Login------------------------------------------    

	@GetMapping("/admin-login")
	public String showLoginForm() {
		return "admin-login";
	}

	@PostMapping("/admin-login")
	@ResponseBody
	public ResponseEntity<String> loginAdmin(@RequestBody Admin loginRequest, HttpSession session) {
		Admin admin = adminService.adminLogin(loginRequest.getEmail(), "ADMIN", // role fixed as "ADMIN"
				loginRequest.getPassword());
		if (admin != null) {
			session.setAttribute("admin", admin);
			return ResponseEntity.ok("Admin login successful.");
		} else {
			return ResponseEntity.status(401).body("Invalid credentials.");
		}
	}

	@GetMapping("/admin-dashboard")
	public String showAdminDashboard(HttpSession session, Model model) {
		Admin admin = (Admin) session.getAttribute("admin");
		if (admin == null) {
			return "redirect:/admin-login";
		}
		model.addAttribute("admin", admin);
		return "admin-dashboard";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/admin-login";
	}

//------------------------------------------Member Login------------------------------------------    

	@GetMapping("/member-login/{role}")
	public String loginMember(@PathVariable("role") String role) {
		return "member-login";
	}

	@PostMapping("/member-login/{role}")
	@ResponseBody
	public ResponseEntity<String> loginMember(@RequestBody User loginRequest, HttpSession session) {

		User member = adminService.memberLogin(loginRequest.getEmail(), User.Role.MEMBER, loginRequest.getPassword());

		if (member != null) {
			session.setAttribute("member", member);
			return ResponseEntity.ok("Member Login Successful...");
		} else {
			return ResponseEntity.status(401).body("Invalid credentials.");
		}
	}

	@GetMapping("/member-dashboard")
	public String showMemberDashboard(HttpSession session, Model model) {
		User member = (User) session.getAttribute("member");
		if (member == null) {
			return "redirect:/member-login/" + User.Role.MEMBER.name();
		}

		model.addAttribute("member", member);
		return "member-dashboard";
	}

//------------------------------------------Secretary Login------------------------------------------    

	@GetMapping("/secretary-login/{role}")
	public String secretaryMember(@PathVariable("role") String role) {
		return "secretary-login";
	}
	

	@PostMapping("/secretary-login/{role}")
	@ResponseBody
	public ResponseEntity<String> secretaryMember(@RequestBody User loginRequest, HttpSession session) {
	
		User secretary = adminService.secretaryLogin(loginRequest.getEmail(), User.Role.SECRETARY, 
				loginRequest.getPassword());
	
		if (secretary != null) {
			session.setAttribute("secretary", secretary);
			return ResponseEntity.ok("Secretary Login Successful...");
		} else {
			return ResponseEntity.status(401).body("Invalid credentials.");
		}
	}
	
	@GetMapping("/secretary-dashboard")
	public String showSecretaryDashboard(HttpSession session, Model model) {
		User secretary = (User) session.getAttribute("secretary");
		if (secretary == null) {
			return "redirect:/secretary-login/" + User.Role.SECRETARY.name();
		}
	
		model.addAttribute("secretary", secretary);
		return "secretary-dashboard";
	}

	
//------------------------------------------Security Login------------------------------------------    
	
	@GetMapping("/security-login/{role}")
	public String securityMember(@PathVariable("role") String role) {
		return "security-login";
	}

	@PostMapping("/security-login/{role}")
	@ResponseBody
	public ResponseEntity<String> securityLogin(@PathVariable("role") String role,
	                                             @RequestBody User loginRequest,
	                                             HttpSession session) {
	   
		User security = adminService.securityLogin(loginRequest.getEmail(), User.Role.SECURITY, 
				loginRequest.getPassword());

		if (security != null) {
			session.setAttribute("security", security); 
			return ResponseEntity.ok("Security Login Successful...");
		} else {
			return ResponseEntity.status(401).body("Invalid credentials.");
		}
	}

	@GetMapping("/security-dashboard")
	public String showSecurityDashboard(HttpSession session, Model model) {
		User security = (User) session.getAttribute("security"); 
		if (security == null) {
			return "redirect:/security-login/" + User.Role.SECURITY.name();
		}

		model.addAttribute("security", security);
		return "security-dashboard";
	}
	
	
//------------------------------------------Accountant Login------------------------------------------

	
	@GetMapping("/accountant-login/{role}")
	public String accountantLoginPage(@PathVariable("role") String role) {
	    return "accountant-login"; 
	}

	@PostMapping("/accountant-login/{role}")
	@ResponseBody
	public ResponseEntity<String> accountantLogin(@PathVariable("role") String role,
	                                              @RequestBody User loginRequest,
	                                              HttpSession session) {

	    User accountant = adminService.accountantLogin(loginRequest.getEmail(), User.Role.ACCOUNTANT,
	            loginRequest.getPassword());

	    if (accountant != null) {
	        session.setAttribute("accountant", accountant);
	        return ResponseEntity.ok("Accountant Login Successful...");
	    } else {
	        return ResponseEntity.status(401).body("Invalid credentials.");
	    }
	}

	@GetMapping("/accountant-dashboard")
	public String showAccountantDashboard(HttpSession session, Model model) {
	    User accountant = (User) session.getAttribute("accountant");
	    if (accountant == null) {
	        return "redirect:/accountant-login/" + User.Role.ACCOUNTANT.name();
	    }

	    model.addAttribute("accountant", accountant);
	    return "accountant-dashboard";
	}


}
