package com.santosh.javatutorials.controller;

import com.santosh.javatutorials.request.ImageDto;
import com.santosh.javatutorials.request.TopicDto;
import com.santosh.javatutorials.response.TopicResponseDto;
import com.santosh.javatutorials.service.IAdminService;
import com.santosh.javatutorials.service.IPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.stream.Collectors;

@Controller
public class AdminController {

	@Autowired
	IAdminService adminService;
	
	@Autowired
	IPageService pageService;

	@GetMapping("/add-image")
	public String addImage(@ModelAttribute(value = "image") ImageDto req) {
		return "add-image";
	}

	@PostMapping("/addTopic")
	public String addTopic(@ModelAttribute(value = "topic") TopicDto req, Model model) {
		adminService.addTopic(req);
		loading(model, null);
		return "index";
	}

	@GetMapping("/add")
	public String add(@ModelAttribute(value = "topic") TopicDto req, Model model) {
		loading(model, null);
		return "add-topic";
	}

	@GetMapping(value = "/delete")
	public String handleDeleteUser(@RequestParam(name = "id") String id) {
		return "admin";
	}

	@PostMapping("/images")
	public String addImages(@RequestParam("file") MultipartFile file, RedirectAttributes attributes,
			@ModelAttribute(value = "topic") ImageDto req, Model model) {
		attributes.addFlashAttribute("message", pageService.addImages(file, req));
		return attributes.addFlashAttribute("message", pageService.addImages(file, req)).toString();
	}

	@GetMapping("/admin")
	public String topics(Model model) {
		model.addAttribute("topics", pageService.allTopic());
		return "admin";
	}
	public void loading(Model model, String name) {
		model.addAttribute("menus", pageService.allMenu());
		if (null != name) {
			model.addAttribute("topics", pageService.allTopic(name));
			model.addAttribute("its_topics",
					pageService.allTopic(name).stream().collect(Collectors.groupingBy(TopicResponseDto::getName)));
		}
	}
}
