package com.info.studentInfo.service;

import com.info.studentInfo.entity.Student;

import java.util.List;

public interface StudentService {
    Student save(Student theStudent);
    Student findById(Integer id);
    List<Student> findAll();
    void delete(Integer id);

    // Extra methods to implement later from controller to DAO
    List<Student> findByLastName(String lastName);
    int deleteAll();

    // update is handled by save() only
//    void update(Student theStudent);

}
