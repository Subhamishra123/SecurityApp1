package com.nt.controller;

import java.util.Map;
import java.util.Random;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bank")
public class BankController {
	@GetMapping("/")
	public String showHome()
	{
		return "home";
	}
	
	@GetMapping("/loanApprove")
	public String approveLoan(Map<String, Object> map)
	{
		map.put("loanAmount", new Random().nextInt(1000000));
		return "loan";
	}
	
	@GetMapping("/balance")
	public String getBalance(Map<String, Object> map)
	{
		map.put("balanceAmount", new Random().nextInt(1000000));
		return "show_balance";
	}
	
	@GetMapping("/offers")
	public String showOffers()
	{
		return "offers";
	}
	@GetMapping("/denied")
	public String accessDenied(Map<String,Object> map)
	{
		map.put("username", SecurityContextHolder.getContext().getAuthentication().getName());
		return "access_denied";
	}

}
