package com.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @ModelAttribute("course")
    public String courseName(){
        return "Java";
    }

    @RequestMapping("/index")
    public String home(){
        return "index";
    }

    @PostMapping("/result")
    public String result(@RequestParam("num1") int num1, @RequestParam("num2") int num2, Model model) {
        int sum = num1 + num2;
        model.addAttribute("result", sum);

        // This tells Spring to look for /WEB-INF/views/result.jsp
        return "result";
    }

    @RequestMapping("/addalien")
    public String addAlien(Alian alian){
        return "result";
    }
}
