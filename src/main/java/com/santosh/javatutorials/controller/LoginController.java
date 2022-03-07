package com.santosh.javatutorials.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.santosh.javatutorials.request.UserDto;
import com.santosh.javatutorials.service.IUserService;

@Controller
public class LoginController {

	@Autowired
	IUserService userService;

	@GetMapping("/login")
	public String showLogin() {
		return "login";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute(value = "user") UserDto req, Model model) {
		userService.login(req);
		if (userService.login(req)) {
			model.addAttribute("username", req.getUsername());
			return "admin";
		}
		model.addAttribute("error", "Incorrect Username & Password");
		return "login";
	}
}
