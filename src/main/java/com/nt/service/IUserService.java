package com.nt.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.nt.model.User;

public interface IUserService extends UserDetailsService {
	
	public String saveUser(User user);

}
