package com.info.studentInfo.Restcontroller;

import com.info.studentInfo.entity.Student;
import com.info.studentInfo.exception.StudentNotFoundException;
import com.info.studentInfo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// use this controller for REST API,
// controller in controller(not in RestController) package is used to send html pages.
@RestController
@RequestMapping("/api")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> findAll(){
        return studentService.findAll();
    }

    @GetMapping("/students/{studentId}")
    public Student findById(@PathVariable int studentId){
        Student tempStudent = studentService.findById(studentId);

        if(tempStudent == null){
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }
        return tempStudent;
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student theStudent){
        // when creating a new student we won't consider the id passed by the client.
        // so we convert it to 0 zero, so the .merge() will take care to create new employee
        // and id will be provided by the SQL. Also to ensure save instead of update.
        theStudent.setId(0);
        Student tempStudent = studentService.save(theStudent);
        return tempStudent;
    }

    @PutMapping("/students")
    public Student updateStudent(@RequestBody Student theStudent){
        // here id comes from the body
        // save will perform merge() meaning adding new or updating existing record.
        Student tempStudent = studentService.save(theStudent);
        return tempStudent;
    }

    @DeleteMapping("/students/{studentId}")
    public String deleteStudent(@PathVariable int studentId){
        Student tempStudent = studentService.findById(studentId);

        if(tempStudent == null){
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }
        studentService.delete(studentId);

        return "Deleted student id of " + studentId;
    }


}
