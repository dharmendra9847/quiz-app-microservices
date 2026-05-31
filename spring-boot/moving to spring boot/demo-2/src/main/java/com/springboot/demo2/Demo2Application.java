package com.springboot.demo2;

import com.springboot.demo2.model.Laptop;
import com.springboot.demo2.service.LaptopService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Demo2Application {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(Demo2Application.class, args);
        Laptop laptop = context.getBean(Laptop.class);

        LaptopService service = context.getBean(LaptopService.class);
        service.addLaptop(laptop);
    }

}
