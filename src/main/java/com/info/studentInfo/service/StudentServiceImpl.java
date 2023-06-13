package com.info.studentInfo.service;

import com.info.studentInfo.dao.StudentDAO;
import com.info.studentInfo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl  implements StudentService{

    private StudentDAO studentDAO;

    @Autowired
    public StudentServiceImpl(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Transactional
    @Override
    public Student save(Student theStudent) {
        return studentDAO.save(theStudent);
    }

    @Override
    public Student findById(Integer id) {
       return studentDAO.findById(id);
    }

    @Override
    public List<Student> findAll() {
        return studentDAO.findAll();
    }

//    update is handled by save() only (using merge())
//    @Transactional
//    @Override
//    public void update(Student theStudent) {
//        studentDAO.update(theStudent);
//    }

    @Transactional
    @Override
    public void delete(Integer id) {
        studentDAO.delete(id);
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        return null;
    }

    @Transactional
    @Override
    public int deleteAll() {
        return studentDAO.deleteAll();
    }
}
