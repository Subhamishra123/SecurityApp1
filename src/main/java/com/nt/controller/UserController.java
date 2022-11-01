package com.nt.controller;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nt.model.User;
import com.nt.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService service;
	
	@GetMapping("/register")
	public String showRegisterPage(@ModelAttribute("userInfo") User details)
	{
		return "user_register";
	}
	
	@PostMapping("/register")
	public String processRegisterPage(Map<String, Object> map,
										@ModelAttribute("userInfo") User details)
	{
		
		String resultMsg=service.saveUser(details);
		System.out.println(resultMsg);
		map.put("message", resultMsg);
		return "user_register_success";
	}
	
	@GetMapping("/showLogin")
	public String showLoginPage()
	{
		return "custom_login";
	}

}
