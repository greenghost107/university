package com.example.web.rest;

import com.example.domain.Enrollment;
import com.example.domain.Semester;
import com.example.domain.Student;
import com.example.exceptions.SpringException;
import com.example.exceptions.StudentNotFoundException;
import com.example.repository.EnrollmentRepository;
import com.example.repository.StudentRepository;
import com.example.service.servicesImpl;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by Michael on 09/06/2016.
 */
@RestController
@RequestMapping("/students")
public class StudentController {
    private static final Logger log = LoggerFactory.getLogger(StudentController.class);
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    private servicesImpl serv;

    public StudentController(){}

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Student>> showAllStudents()
    {
        return Optional.ofNullable(serv.findAllStudents())
                .map(stud -> new ResponseEntity<>(stud, HttpStatus.OK))
                .orElseThrow(() -> new SpringException("Students DataBase Is Empty"));
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Student> addStudent(@ApiParam(name="addStudent", value="Student is added to the database", required=true)@Valid @RequestBody Student student)
    {
        return Optional.ofNullable(serv.addStudent(student))
                .map(stud -> new ResponseEntity<>(stud, HttpStatus.OK))
                .orElseThrow(() -> new SpringException("Can't add Student"));
    }



    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Student> showStudentByID(@PathVariable( "id" ) Long studentId )
        {
        return Optional.ofNullable(serv.findStudentById(studentId))
                .map(stud -> new ResponseEntity<>(stud, HttpStatus.OK))
                .orElseThrow(() -> new StudentNotFoundException(studentId));
    }


    @RequestMapping(value = "/{name}", method = RequestMethod.POST)
    public ResponseEntity<Student> addStudent(@PathVariable( "name" ) String studentName )
    {
        return new ResponseEntity<>(serv.addStudentByName(studentName), HttpStatus.OK);
    }


    @RequestMapping(value = "/{name}" , method = RequestMethod.DELETE)
    public ResponseEntity<Student> deleteStudent(@PathVariable( "name" ) String studentName )
    {
        log.info("delete student");
        return new ResponseEntity<>(serv.deleteStudent(studentName), HttpStatus.OK);

    }

    @RequestMapping(value = "/{studentName}/{courseName}/{semester}" ,method = RequestMethod.POST)
    public ResponseEntity<Enrollment> updateStudentGrade(@PathVariable ( "studentName" )String studentName , @PathVariable(value = "courseName") String courseName
            , @PathVariable(value = "semester")  Semester semester,@RequestBody Long grade)
    {
        System.out.println("Itai");
        return Optional.ofNullable(serv.updateGrade(courseName,studentName,semester,grade))
                .map(bool -> new ResponseEntity<>(bool, HttpStatus.OK))
                .orElseThrow(() -> new SpringException("couldn't update grade"));
    }




}
