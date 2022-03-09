package com.santosh.javatutorials.controller;


import com.santosh.javatutorials.request.MenuDto;
import com.santosh.javatutorials.service.IAdminService;
import com.santosh.javatutorials.service.IPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AdminController2 {

	@Autowired
	IAdminService adminService;
	
	@Autowired
	IPageService pageService;

	@PostMapping("/add-topic")
	public void addTopic(@RequestBody MenuDto req) {
		 adminService.addMenu(req);
	}
}
