package com.itstech.dharm.springdatajpa;

import com.itstech.dharm.springdatajpa.model.StudentInfo;
import com.itstech.dharm.springdatajpa.repositories.StudentRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringdatajpaApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(SpringdatajpaApplication.class, args);

        StudentRepository studentRepository = context.getBean(StudentRepository.class);

        // 1. Insert the student data in DB
        // This WILL fire a CREATE query
//       StudentInfo s1 = context.getBean(StudentInfo.class);
//       s1.setRollNo(101);
//       s1.setFirstName("Ravi");
//       s1.setLastName("Kumar");
//       s1.setAge(23);
//       s1.setMarks(92);
//
       StudentInfo s2 = context.getBean(StudentInfo.class);
       s2.setRollNo(102);
       s2.setFirstName("Navin");
       s2.setLastName("Reddy");
       s2.setAge(21);
       s2.setMarks(95);

//        StudentInfo s3 = context.getBean(StudentInfo.class);
//       s3.setRollNo(103);
//       s3.setFirstName("Kiran");
//       s3.setLastName("Sharma");
//       s3.setAge(39);
//       s3.setMarks(99);
//
//
//       studentRepository.save(s1);
//       studentRepository.save(s2);
//       studentRepository.save(s3);
//       System.out.println("Student saved successfully!");


        // UPDATE
//       s3.setRollNo(103);
//       s3.setFirstName("Kiran");
//       s3.setLastName("Sharma");
//       s3.setAge(30);
//       s3.setMarks(85);
//
//       studentRepository.save(s3);

//       System.out.println("Student updated successfully!");

        // 1. Fetch the existing student from DB
        // This WILL fire a SELECT query
        //StudentInfo existingStudent = studentRepository.findById(103).orElse(new StudentInfo());

        // 2. Modify the fields
        //existingStudent.setAge(49);
        //existingStudent.setMarks(98);

        // 3. Save it back
        // This WILL fire an UPDATE query
        //studentRepository.save(existingStudent);

        //System.out.println("Update actually sent to Database!");

        // 1. Fetch the existing student from DB
        // This WILL fire a SELECT query
        //StudentInfo existingStudent = studentRepository.findById(103).orElse(new StudentInfo());

        // This WILL fire an DELETE query
        //studentRepository.delete(existingStudent);

        studentRepository.delete(s2);

        System.out.println("Data delete from the Database!");
    }

}
