package com.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.model.User;

public interface UserRepository extends JpaRepository<User, Integer> 
{
	public User findByEmail(String email);
	public boolean existsByEmail(String email);
	public List<User> findByRole(String email);
	public List<User> findAll();
	
}
