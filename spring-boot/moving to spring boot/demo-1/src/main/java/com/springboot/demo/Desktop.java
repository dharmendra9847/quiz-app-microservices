package com.springboot.demo;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Desktop implements Computer {

    public void compile() {

        System.out.println("Desktop compile");
    }
}
