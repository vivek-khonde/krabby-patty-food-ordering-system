package com.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.model.User;
import com.web.service.UserService;

import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public User registerUser(@RequestBody User user) {
		userService.saveUser(user);
		return user;
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User user, HttpSession session) {
		User validUser = userService.loginUser(user.getEmail(), user.getPassword());
		
	    System.out.println("LOGIN session id: " + session.getId());
		
		if(validUser != null) {
			session.setAttribute("loggedInUser", validUser);
			 System.out.println("User stored in session: " + validUser.getEmail());
			return ResponseEntity.ok(validUser);
		}
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	@GetMapping("/current")
	public ResponseEntity<?> getCurrentUser(HttpSession session) {
		System.out.println("CURRENT session id: " + session.getId());
	    User user = (User) session.getAttribute("loggedInUser");
	    System.out.println("Session attribute loggedInUser: " + user);
	    if (user != null) {
	        return ResponseEntity.ok(user);
	    }
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	@GetMapping("/logout")
	public ResponseEntity<?> logout(HttpSession session) {
	    session.invalidate();
	    return ResponseEntity.ok("Logged out");
	}

}
