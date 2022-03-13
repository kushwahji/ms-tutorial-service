package com.santosh.javatutorials.controller;

import com.santosh.javatutorials.entity.Adverstise;
import com.santosh.javatutorials.request.ImageDto;
import com.santosh.javatutorials.request.MenuDto;
import com.santosh.javatutorials.request.TopicDto;
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

	@GetMapping("/admin")
	public String admin() {
		return "admin/admin";
	}

	@GetMapping("/user")
	public String user() {
		return "user/admin";
	}

	@GetMapping("/login")
	public String login() {
		return "admin/login";
	}

	@GetMapping("/view-menu")
	public String viewMenu(Model model) {
		model.addAttribute("menus", pageService.menus());
		return "admin/view-menu";
	}

	@GetMapping("/view-topic")
	public String viewTopic(Model model) {
		model.addAttribute("topics", pageService.allTopic());
		return "admin/view-topic";
	}

	@GetMapping("/view-advertise")
	public String viewAds(Model model) {
		model.addAttribute("ads", pageService.getAds());
		return "admin/view-advertise";
	}

	@GetMapping("/add-menu")
	public String addMenus(@ModelAttribute(value = "menu") MenuDto req, Model model) {
		return "admin/add-menu";
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable(name = "id") String id, @RequestParam(name = "status") boolean status,
			Model model) {
		adminService.updateStatus(Integer.parseInt(id), status);
		return "redirect:/view-menu";
	}

	@GetMapping("/tupdate/{id}")
	public String updateTopic(@PathVariable(name = "id") String id, @RequestParam(name = "status") boolean status,
			Model model) {
		adminService.updateStatusTopic(Integer.parseInt(id), status);
		return "redirect:/view-topic";
	}

	@GetMapping("/add-topic")
	public String add(@ModelAttribute(value = "topic") TopicDto req, Model model) {
		model.addAttribute("menus", pageService.allMenu());
		return "admin/add-topic";
	}

	@GetMapping("/add-image")
	public String addImage(@ModelAttribute(value = "image") ImageDto req) {
		return "admin/add-image";
	}

	@GetMapping("/add-advertise")
	public String addAdvertise(@ModelAttribute(value = "ads") Adverstise req) {
		return "admin/add-advertise";
	}

	@PostMapping("/addTopic")
	public String addTopic(@ModelAttribute(value = "topic") TopicDto req, Model model) {
		adminService.addTopic(req);
		return "redirect:/view-topic";
	}

	@PostMapping("/status")
	public String statusAction(@ModelAttribute(value = "menu") MenuDto req, Model model) {
		return "admin/view-menu";
	}

	@PostMapping("/addMenu")
	public String addMenu(@ModelAttribute(value = "menu") MenuDto req, Model model) {
		adminService.addMenu(req);
		return "redirect:/view-menu";
	}

	@PostMapping("/addAds")
	public String addMenu(@ModelAttribute(value = "ads") Adverstise req, Model model) {
		adminService.addAdvertise(req);
		return "redirect:/view-advertise";
	}

	@PostMapping("/images")
	public String addImages(@RequestParam("file") MultipartFile file, RedirectAttributes attributes,
			@ModelAttribute(value = "topic") ImageDto req, Model model) {
		attributes.addFlashAttribute("message", adminService.addImages(file, req));
		return attributes.addFlashAttribute("message", adminService.addImages(file, req)).toString();
	}

	
	@GetMapping("/edit/{id}")
	public String editMenu(@PathVariable("id") long id, MenuDto menu, Model model) {
		model.addAttribute("menu", pageService.getMenu(id));
		return "admin/add-menu";
	}

	@GetMapping("/aedit/{id}")
	public String editAdvertise(@PathVariable("id") long id, MenuDto menu, Model model) {
		model.addAttribute("ads", pageService.getAds(id));
		return "admin/add-advertise";
	}

	@GetMapping("/tedit/{id}")
	public String editTopic(@PathVariable("id") long id, TopicDto menu, Model model) {
		model.addAttribute("topic", pageService.getTopic(id));
		model.addAttribute("menus", pageService.allMenu().stream()
				.filter(f -> f.getMenuId().equals(pageService.getTopic(id).getMenuId())).collect(Collectors.toList()));
		return "admin/add-topic";
	}

	@GetMapping("/tdelete/{id}")
	public String deleteTopic(@PathVariable("id") long id, Model model) {
		adminService.deleteTopic(id);
		return "redirect:/view-topic";
	}

	@GetMapping("/adelete/{id}")
	public String deleteAdvertise(@PathVariable("id") long id, Model model) {
		adminService.deleteAdvertise(id);
		return "redirect:/view-advertise";
	}

	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") long id, Model model) {
		adminService.deleteMenu(id);
		return "redirect:/view-menu";
	}

}
