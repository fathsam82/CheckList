package com.skilldistillery.checklists.services;

import com.skilldistillery.checklists.entities.User;

public interface AuthService {
	public User register(User user);
	public User getUserByUsername(String username);

}
