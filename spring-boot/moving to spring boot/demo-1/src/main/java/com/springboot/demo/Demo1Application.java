package com.springboot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Demo1Application {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(Demo1Application.class, args);
        Alien alien = context.getBean(Alien.class);
        System.out.println(alien.getAge());
        alien.code();
    }

}
