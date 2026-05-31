package com.springboot.mainapp;

import com.springboot.mainapp.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class JavaBasedConfigApplication {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Alien alien1 = context.getBean(Alien.class);
        //alien1.setName("Ramesh");
        //alien1.setAge(25);

        System.out.println("\n" + alien1.getName() + " :  " + alien1.getAge());

        alien1.code();
    }
}
