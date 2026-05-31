package com.spring.myapp.alien;

import com.spring.myapp.laptop.Computer;

import java.beans.ConstructorProperties;

public class Alien {

    private int age;
    private int salary;
    private Computer laptop;

    //Default Constructor
    public Alien() {
        System.out.println("Alien Object Created");
    }

    //Parameterized Constructor
    @ConstructorProperties({"age", "laptop", "salary"})
    public Alien(int age, Computer laptop, int salary) {
        this.age = age;
        this.laptop = laptop;
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public Computer getLaptop() {
        return laptop;
    }

    public int getSalary() {
        return salary;
    }

    public void code(){
        System.out.println("Alien coding...");
        laptop.compile();
    }
}
