package com.springboot.mainapp.computer;

public class Desktop implements Computer {

    public Desktop() {
        System.out.println("Desktop Object Created!");
    }

    public void compile(){
        System.out.println("Desktop compile!");
    }
}
