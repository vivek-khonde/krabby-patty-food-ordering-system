package com.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.web.model.User;
import com.web.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public boolean saveUser(User user) {

	    // If no role provided, default to USER
	    if (user.getRole() == null || user.getRole().isEmpty()) {
	        user.setRole("USER");
	    }

	    user.setEmail(user.getEmail().toLowerCase().trim());

	    User existingUser = userRepository.findByEmail(user.getEmail());
	    if(existingUser != null) {
	        return false;
	    }

	    try {
	        user.setPassword(passwordEncoder.encode(user.getPassword()));
	        userRepository.save(user);
	        return true;
	    } catch(DataIntegrityViolationException e) {
	        return false;
	    }
	}

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUsers(String role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean existsEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User loginUser(String email, String password) {
		 email = email.toLowerCase().trim(); 
		User validUser = userRepository.findByEmail(email);
		
		if(validUser != null && passwordEncoder.matches(password, validUser.getPassword())) {
		return validUser;
		}
		return null;
	}

	@Override
	public User updateUserProfile(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean matchPassword(String rawPassword, String encodedPassword) {
		
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}

	@Override
	public String encodePassword(String rawPassword) {
		
		return passwordEncoder.encode(rawPassword);
	}

}
