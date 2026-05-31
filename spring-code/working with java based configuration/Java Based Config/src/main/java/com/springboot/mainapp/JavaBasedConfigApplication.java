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

//        Desktop desktop1 = context.getBean(Desktop.class);
//        desktop1.compile();
//
//        Laptop laptop = context.getBean(Laptop.class);
//        laptop.compile();

        //Desktop desktop2 = context.getBean(Desktop.class);
        //desktop2.compile();

        Alien alien1 = context.getBean(Alien.class);

        System.out.println("\n" + alien1.getName() + " :  " + alien1.getAge());

        alien1.code();
        //SpringApplication.run(JavaBasedConfigApplication.class, args);
    }

}
