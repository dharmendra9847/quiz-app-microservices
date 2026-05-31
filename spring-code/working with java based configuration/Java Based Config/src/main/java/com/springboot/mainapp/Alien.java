package com.springboot.mainapp;

import com.springboot.mainapp.computer.Computer;

public class Alien {

    private int age;
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

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public void code(){

        System.out.println("Alien code!");
        computer.compile();
    }

}
