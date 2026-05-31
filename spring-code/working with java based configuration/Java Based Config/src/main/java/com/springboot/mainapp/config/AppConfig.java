package com.springboot.mainapp.config;

import com.springboot.mainapp.Alien;
import com.springboot.mainapp.computer.Desktop;
import com.springboot.mainapp.computer.Laptop;
import com.springboot.mainapp.computer.Computer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfig {

    @Bean(name = {"desk", "com1", "desktop"})    // Here, Set our bean name
    //@Scope(value = "prototype")
    public Desktop desktop(){
        return new Desktop();
    }

    @Bean(name = {"lap", "com2", "laptop"})    // Here, Set our bean name
    //@Scope(value = "prototype")
    @Primary
    public Laptop laptop(){
        return new Laptop();
    }

    @Bean
//    public Alien alien(@Qualifier("com1") Computer computer){
    public Alien alien(Computer computer){
        Alien obj = new Alien();
        obj.setName("Ramesh");
        obj.setAge(20);
        obj.setComputer(computer);
        return obj;
    }
}
