package com.itstech.dharm.dockerappdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String greet() {
        return "<h1>Greetings, Spring Boot Running Inside Docker with OpenJDK 25!</h1>";
    }
}
