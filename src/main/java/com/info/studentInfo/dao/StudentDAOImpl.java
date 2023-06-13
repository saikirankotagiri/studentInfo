package com.info.studentInfo.dao;

import com.info.studentInfo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{

    private EntityManager entityManager;

    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {

        Student tempStudent = entityManager.find(Student.class, id);
        return  tempStudent;
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student", Student.class);
        return theQuery.getResultList();

    }

    @Override
    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student WHERE lastName=:theLastName", Student.class);
        theQuery.setParameter("theLastName", lastName);
        return theQuery.getResultList();
    }

    // below updated student object is passed as argument
    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);

    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Student tempStudent = entityManager.find(Student.class, id);
        entityManager.remove(tempStudent);
    }

    @Override
    @Transactional
    public int deleteAll() {
        int numRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();
        return  numRowsDeleted;
    }

}
