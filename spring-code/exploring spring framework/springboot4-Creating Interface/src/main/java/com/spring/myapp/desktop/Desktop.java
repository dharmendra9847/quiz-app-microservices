package com.spring.myapp.desktop;

import com.spring.myapp.laptop.Computer;

public class Desktop implements Computer {

    public Desktop() {
        System.out.println("Desktop object created");
    }

    @Override
    public void compile(){
        System.out.println("Alien compiling by Desktop");
    }
}
