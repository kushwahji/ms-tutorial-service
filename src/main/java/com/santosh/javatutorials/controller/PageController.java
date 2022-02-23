package com.santosh.javatutorials.controller;

import com.santosh.javatutorials.request.TopicDto;
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

    @GetMapping("/index")
    public String topic(Model model){
        model.addAttribute("topics", pageService.allTopic(1L));
        model.addAttribute("menus", pageService.allMenu());
        return "index";
    }
    /*@PostMapping("/search")
    public String search(@ModelAttribute(value="topicDto") TopicDto req, Model model){
        model.addAttribute("topics", pageService.allSearch(req.getName()));
        model.addAttribute("menus", pageService.allMenu());
        return "index";
    }
*/    @PostMapping("/addTopic")
    public String addTopic(@ModelAttribute(value="topic") TopicDto req,Model model){
        adminService.addTopic(req);
        model.addAttribute("menus", pageService.allMenu());
        return "index";
    }
    @GetMapping("/add")
    public String add(@ModelAttribute(value="topic") TopicDto addTopic,Model model){
        model.addAttribute("menus", pageService.allMenu());
        return "add-topic";
    }
    @GetMapping("/index/{id}")
    public String topic(@PathVariable("id") Long id, Model model){
        model.addAttribute("topics", pageService.allTopic(id));
        model.addAttribute("menus", pageService.allMenu());
        return "index";
    }

}
