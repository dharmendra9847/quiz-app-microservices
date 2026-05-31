package com.itstech.dharm.springdatajpa.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "StudentInfo")
@Component
@Scope("prototype")
public class StudentInfo {

    @Id
    @Column(name = "Roll_No", unique = true)
    @Nonnull
    private Integer rollNo;

    @Column(name = "First_Name", length = 50)
    private String firstName;

    @Column(name = "Last_Name", length = 20)
    private String lastName;

    @Column(name = "Age")
    private int age;

    @Column(name = "Marks")
    private int marks;
}
