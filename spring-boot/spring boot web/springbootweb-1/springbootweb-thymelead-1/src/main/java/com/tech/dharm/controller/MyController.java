package com.tech.dharm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

    @RequestMapping("/index")
    public String add() {
        System.out.println("You are at Home!");
        return "index";
    }

/*    @RequestMapping("/add")
    public String add(@RequestParam int num1, @RequestParam int num2, Model model) {

        int result = num1 + num2;
        model.addAttribute("result", result);

        return "add";
    }*/

    @RequestMapping("/add")
    public ModelAndView add(@RequestParam int num1, @RequestParam int num2, ModelAndView modelAndView) {

        int result = num1 + num2;

        modelAndView.addObject("result", result);

        return modelAndView;
    }
}
