package com.info.studentInfo.dao;

import com.info.studentInfo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{

    private EntityManager entityManager;

    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Student save(Student theStudent) {
        // if no id is provided for theStudent argument then default id = 0.
        // because the default values for instance int variables is 0.
        // so merge will create a new record if there is no id else will update the record with the provided id.
        // merge will return the latest saved or updated record.
        Student tempStudent = entityManager.merge(theStudent);
        return tempStudent;
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
    public void delete(Integer id) {
        Student tempStudent = entityManager.find(Student.class, id);
        entityManager.remove(tempStudent);
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student WHERE lastName=:theLastName", Student.class);
        theQuery.setParameter("theLastName", lastName);
        return theQuery.getResultList();
    }

    @Override
    public int deleteAll() {
        int numRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();
        return  numRowsDeleted;
    }

    // below updated student object is passed as argument
    // update is handled by save() only
//    @Override
//    public void update(Student theStudent) {
//        entityManager.merge(theStudent);
//
//    }
}
