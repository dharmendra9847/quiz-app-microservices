package com.springboot.demo2.service;

import com.springboot.demo2.model.Laptop;
import com.springboot.demo2.repositories.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LaptopService {

    private LaptopRepository laptopRepository;

    @Autowired
    public void setLaptopRepository(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    public void addLaptop(Laptop laptop) {
        laptopRepository.saveLaptop(laptop);
        System.out.println("addLaptop");
    }

    public boolean isGoodForProduct(Laptop laptop) {
        return true;
    }
}
