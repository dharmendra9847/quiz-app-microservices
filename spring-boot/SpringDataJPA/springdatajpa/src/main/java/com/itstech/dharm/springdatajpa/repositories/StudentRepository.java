package com.itstech.dharm.springdatajpa.repositories;

import com.itstech.dharm.springdatajpa.model.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentInfo, Integer> {


    void deleteByRollNo(int rollNo);
}
