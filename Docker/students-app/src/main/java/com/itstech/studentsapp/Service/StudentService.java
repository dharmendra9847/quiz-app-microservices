package com.itstech.studentsapp.Service;

import com.itstech.studentsapp.model.Student;
import com.itstech.studentsapp.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private StudentRepo studentRepo;

    @Autowired
    public void setStudentRepo(StudentRepo studentRepo) {}
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public List<Student> getStudents() {
        return List.of(
                new Student(1, "Ranga", 25),
                new Student(2, "Navin", 39),
                new Student(3, "Kiran", 36)
        );
    }

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public void addStudent() {
        Student student = new Student();
        student.setName("Raju");
        student.setAge(20);
        studentRepo.save(student);
    }
}
