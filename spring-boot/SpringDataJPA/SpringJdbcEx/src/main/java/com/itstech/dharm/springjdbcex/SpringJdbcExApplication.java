package com.itstech.dharm.springjdbcex;

import com.itstech.dharm.springjdbcex.model.Student;
import com.itstech.dharm.springjdbcex.service.StudentServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class SpringJdbcExApplication {

	public static void main(String[] args) {

	 	ApplicationContext context = SpringApplication.run(SpringJdbcExApplication.class, args);
		Student student = context.getBean(Student.class);
		student.setRollNo(101);
		student.setName("Navin");
		student.setMarks(90);

		StudentServiceImpl studentService = context.getBean(StudentServiceImpl.class);
		studentService.addStudent(student);

		List<Student> students = studentService.getAllStudents();
		System.out.println(students);
	}

}
