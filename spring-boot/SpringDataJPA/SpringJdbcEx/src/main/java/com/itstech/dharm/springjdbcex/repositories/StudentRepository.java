package com.itstech.dharm.springjdbcex.repositories;

import com.itstech.dharm.springjdbcex.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addStudent(Student student) {

        String sql = "INSERT INTO students(rollNo, name, marks) VALUES (?,?,?)";
        int updated = jdbcTemplate.update(sql, student.getRollNo(), student.getName(), student.getMarks());
        System.out.println(updated);
    }

    public List<Student> getAllStudents() {

        String sql =  "SELECT * FROM students";

        return jdbcTemplate.query(sql, (rs, rowNo) -> {
            Student student = new Student();
            student.setRollNo(rs.getInt("rollNo"));
            student.setName(rs.getString("name"));
            student.setMarks(rs.getInt("marks"));
            return student;
        });
    }
}
