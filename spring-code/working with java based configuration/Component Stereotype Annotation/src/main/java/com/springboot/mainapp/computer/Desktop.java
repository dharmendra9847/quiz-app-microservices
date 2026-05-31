package com.springboot.mainapp.computer;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Primary
@Scope("prototype")
public class Desktop implements Computer {

    public Desktop() {
        System.out.println("Desktop Object Created!");
    }

    public void compile(){
        System.out.println("Desktop compile!");
    }
}
