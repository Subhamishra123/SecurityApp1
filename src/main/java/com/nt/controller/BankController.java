package com.nt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BankController {
	@GetMapping("/")
	public String showHome()
	{
		return "home";
	}
	
	@GetMapping("/loanApprove")
	public String approveLoan()
	{
		return "loan";
	}
	
	@GetMapping("/balance")
	public String getBalance()
	{
		return "show_balance";
	}
	
	@GetMapping("/offers")
	public String showOffers()
	{
		return "offers";
	}
	@GetMapping("/denied")
	public String accessDenied()
	{
		return "access_denied";
	}

}
