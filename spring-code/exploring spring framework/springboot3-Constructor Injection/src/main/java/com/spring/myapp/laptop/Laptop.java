package com.spring.myapp.laptop;

public class Laptop implements Computer {

    public Laptop() {
        System.out.println("Laptop Object Created");
    }

    @Override
    public void compile(){
        System.out.println("Alien compiling by Laptop");
    }
}
