package com.springboot.demo2.model;

import org.springframework.stereotype.Component;

@Component
public class Laptop implements Computer {

    public void compile() {
        System.out.println("Laptop compile");
    }
}
