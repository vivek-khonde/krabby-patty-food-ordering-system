package com.web.service;

import java.util.List;

import com.web.model.User;

public interface UserService 
{
	public boolean saveUser(User user);
	public User getUserByEmail(String email);
	public List<User> getUsers(String role);
	public List<User> getAllUsers();
	public User updateUser(User user);
	public void deleteUser(int id);
	public boolean existsEmail(String email);
	public User loginUser(String email, String password);
	public User updateUserProfile(User user);
	public boolean matchPassword(String rawPassword, String encodedPassword);
	public String encodePassword(String rawPassword);
}
