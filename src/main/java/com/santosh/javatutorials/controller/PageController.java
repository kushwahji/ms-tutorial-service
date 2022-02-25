package com.santosh.javatutorials.controller;

import com.santosh.javatutorials.utils.FileUploadUtil;
import com.santosh.javatutorials.request.TopicDto;
import com.santosh.javatutorials.service.AdminService;
import com.santosh.javatutorials.service.IPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.IOException;
import java.util.stream.Collectors;

@Controller
public class PageController {
    private final String UPLOAD_DIR = "/src/main/resources/templates/img/";
    @Autowired
    IPageService pageService;

    @Autowired
    AdminService adminService;

    @GetMapping({"/","/index"})
    public String topic(Model model){
        model.addAttribute("topics", pageService.allTopics( 1L));
        model.addAttribute("its_topics", pageService.allTopics( 1L).stream().collect(Collectors.groupingBy(o->o.getName())));
        model.addAttribute("menus", pageService.allMenu());
        return "index";
    }

    @RequestMapping(path = {"/","/search"})
    public String home(Model model, String keyword) {
        if(keyword!=null) {
            model.addAttribute("topics", pageService.allSearch(keyword));
            model.addAttribute("its_topics", pageService.allSearch(keyword).stream().collect(Collectors.groupingBy(o->o.getName())));
            model.addAttribute("menus", pageService.allMenu());
        }else {
            model.addAttribute("topics", pageService.allTopic( null));}
        return "index";
    }

    @PostMapping("/addTopic")
    public String addTopic(@RequestParam("file") MultipartFile file, RedirectAttributes attributes, @ModelAttribute(value="topic") TopicDto req, Model model){
        // check if file is empty
        String rootPath = System.getProperty("user.dir");
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/";
        }
        // normalize the file path
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
           // Path path = Paths.get(UPLOAD_DIR + fileName);
            req.setImage("/img/"+fileName);
            FileUploadUtil.saveFile(rootPath+UPLOAD_DIR, fileName, file);
            //Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        adminService.addTopic(req);
        model.addAttribute("menus", pageService.allMenu());
        // return success response
        attributes.addFlashAttribute("message", "You successfully uploaded " + fileName + '!');

        return "index";
    }
    @GetMapping("/add")
    public String add(@ModelAttribute(value="topic") TopicDto req, Model model){
        model.addAttribute("menus", pageService.allMenu());
        return "add-topic";
    }
    @GetMapping("/index/{id}")
    public String topic(@PathVariable("id") Long id, Model model){
        model.addAttribute("topics", pageService.allTopic(id));
        model.addAttribute("its_topics", pageService.allTopics(id).stream().collect(Collectors.groupingBy(o->o.getName())));
        model.addAttribute("menus", pageService.allMenu());
        return "index";
    }
}
