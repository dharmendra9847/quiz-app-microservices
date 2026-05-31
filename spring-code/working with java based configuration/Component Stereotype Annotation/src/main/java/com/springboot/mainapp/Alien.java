package com.springboot.mainapp;

import com.springboot.mainapp.computer.Computer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Alien {

    @Value("30")
    private int age;

    @Value("Anjali")
    private String name;

    private Computer computer;

    public Alien() {
        System.out.println("Alien Object Created!");
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Autowired
    @Qualifier("laptop")
    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public void code(){

        System.out.println("Alien code!");
        computer.compile();
    }

}
