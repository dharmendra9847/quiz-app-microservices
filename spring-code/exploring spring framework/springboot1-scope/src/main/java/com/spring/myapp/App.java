package com.spring.myapp;

import com.spring.myapp.alian.Alian;
import com.spring.myapp.laptop.Laptop;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Alian alian1 = (Alian) context.getBean("alian1");

        alian1.age = 21;
        System.out.println("Age of Alian1: " + alian1.age);

        //alian1.code();

        Alian alian2 = (Alian) context.getBean("alian2");
        System.out.println("Age of Alian2: " + alian2.age);
        //alian2.code();

//        Laptop laptop1 = (Laptop) context.getBean(Laptop.class);
//        laptop1.compile();
    }
}