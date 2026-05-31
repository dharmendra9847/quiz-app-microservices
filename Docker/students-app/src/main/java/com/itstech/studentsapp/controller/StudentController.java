package com.itstech.studentsapp.controller;

import com.itstech.studentsapp.Service.StudentService;
import com.itstech.studentsapp.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping("/getstudent")
    public List<Student> getAllStudents() {

        return studentService.getAllStudents();
    }

    @RequestMapping("/addstudent")
    public void addStudent() {
        studentService.addStudent();
    }
}
