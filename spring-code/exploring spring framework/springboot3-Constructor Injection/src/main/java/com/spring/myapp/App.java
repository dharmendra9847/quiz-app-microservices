package com.spring.myapp;

import com.spring.myapp.alien.Alien;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Alien alien1 = (Alien) context.getBean("alien1");
        alien1.code();

        System.out.println("Age of Alien : " + alien1.getAge());
        System.out.println("Salary of Alien : " + alien1.getSalary());

    }
}