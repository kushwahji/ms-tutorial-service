package com.santosh.javatutorials.controller;

import com.santosh.javatutorials.service.AdminService;
import com.santosh.javatutorials.service.IPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PageController {

	@Autowired
	IPageService pageService;

	@Autowired
	AdminService adminService;

	@GetMapping({ "/", "/index" })
	public String topic(@RequestParam(value = "name",defaultValue = "menuCore Java") String name, Model model) {
		model.addAttribute("menus", pageService.allMenu());
		if (pageService.allTopic(name).isEmpty() || pageService.sideBar(name).isEmpty()) {
			model.addAttribute("error", "No Data Present");
		}
	
		model.addAttribute("topics", pageService.allTopic(name));
		model.addAttribute("its_topics", pageService.sideBar(name));
		return "index";
	}
}
