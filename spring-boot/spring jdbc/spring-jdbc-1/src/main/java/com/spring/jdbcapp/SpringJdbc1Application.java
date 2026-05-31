package com.spring.jdbcapp;

import com.spring.jdbcapp.model.Student;
import com.spring.jdbcapp.service.StudentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class SpringJdbc1Application {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(SpringJdbc1Application.class, args);
        Student student = context.getBean(Student.class);
        student.setRoll(104);
        student.setName("Sushil");
        student.setMarks(92);

        StudentService service = context.getBean(StudentService.class);
        service.addStudent(student);

        List<Student> students = service.getAllStudents();
        System.out.println("Student data found successfully!\n" +  students);
    }

}
