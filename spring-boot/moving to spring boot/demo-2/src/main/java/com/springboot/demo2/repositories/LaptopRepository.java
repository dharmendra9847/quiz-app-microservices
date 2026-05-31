package com.springboot.demo2.repositories;

import com.springboot.demo2.model.Laptop;
import org.springframework.stereotype.Repository;

@Repository
public class LaptopRepository {

    public void saveLaptop(Laptop laptop) {
        System.out.println("saveLaptop");
    }
}
