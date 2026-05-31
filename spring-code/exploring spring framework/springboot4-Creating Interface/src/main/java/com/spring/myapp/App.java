package com.spring.myapp;

import com.spring.myapp.alien.Alien;
import com.spring.myapp.desktop.Desktop;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Alien alien1 = (Alien) context.getBean("alien1");
        alien1.code();

        System.out.println("Age of Alien : " + alien1.getAge());
        System.out.println("Salary of Alien : " + alien1.getSalary());


        Desktop desktop1 = (Desktop) context.getBean("com2");

    }
}