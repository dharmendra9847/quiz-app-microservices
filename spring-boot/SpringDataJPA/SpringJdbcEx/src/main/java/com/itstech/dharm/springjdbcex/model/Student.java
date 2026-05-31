package com.itstech.dharm.springjdbcex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Student {

    private int rollNo;
    private String name;
    private int marks;
}