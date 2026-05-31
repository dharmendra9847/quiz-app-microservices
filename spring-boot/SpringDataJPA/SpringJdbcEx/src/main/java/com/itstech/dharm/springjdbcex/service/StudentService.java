package com.itstech.dharm.springjdbcex.service;

import com.itstech.dharm.springjdbcex.model.Student;

import java.util.List;

public interface StudentService {

    void addStudent(Student student);
    List<Student> getAllStudents();
}
