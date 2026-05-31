package com.spring.jdbcapp.repositories;

import com.spring.jdbcapp.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Student student) {

        String sql = "insert into student (roll, name, marks) values(?,?,?)";
        int updated = jdbcTemplate.update(sql, student.getRoll(), student.getName(), student.getMarks());
        System.out.println("Row Affected - " + updated);
        System.out.println("Student data saved successfully");
    }

    public List<Student> findAll() {

        String sql = "select * from student";

//        RowMapper<Student> rowMapper = new RowMapper<Student>() {
//
//            @Override
//            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
//
//                Student student = new Student();
//                student.setRoll(rs.getInt("roll"));
//                student.setName(rs.getString("name"));
//                student.setMarks(rs.getInt("marks"));
//                return student;
//            }
//        };
//
//        return jdbcTemplate.query(sql, rowMapper);
//    }

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Student student = new Student();
            student.setRoll(rs.getInt("roll"));
            student.setName(rs.getString("name"));
            student.setMarks(rs.getInt("marks"));
            return student;
        });
    }
}
