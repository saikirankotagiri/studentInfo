package com.info.studentInfo.dao;

import com.info.studentInfo.entity.Student;

import java.util.List;

public interface StudentDAO {
    Student save(Student theStudent);
    Student findById(Integer id);
    List<Student> findAll();
    void delete(Integer id);


    List<Student> findByLastName(String lastName);
    int deleteAll();

    // update is handled by save() only
//    void update(Student theStudent);
}
