package com.springfirst;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Alian {

    @Autowired
    Laptop laptop;

    public void code(){

        System.out.println("Alian coding...");
        laptop.compile();
    }
}
