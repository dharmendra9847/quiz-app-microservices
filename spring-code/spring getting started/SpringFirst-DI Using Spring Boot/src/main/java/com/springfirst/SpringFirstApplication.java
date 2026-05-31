package com.springfirst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringFirstApplication {

    public static void main(String[] args) {

       ApplicationContext context = SpringApplication.run(SpringFirstApplication.class, args);

       Alian alian1 = context.getBean(Alian.class);
       alian1.code();

        Alian alian2 = context.getBean(Alian.class);
        alian2.code();
    }

}
