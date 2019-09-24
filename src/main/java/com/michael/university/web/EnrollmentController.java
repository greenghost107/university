package com.example.web.rest;

import com.example.domain.Course;
import com.example.domain.Enrollment;
import com.example.domain.Student;
import com.example.exceptions.SpringException;
import com.example.exceptions.StudentNotFoundException;
import com.example.repository.CourseRepository;
import com.example.repository.EnrollmentRepository;
import com.example.service.servicesImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Created by Michael on 13/06/2016.
 */
@RestController
@RequestMapping("/register")
public class EnrollmentController {
    private static final Logger log = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    private servicesImpl serv;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Enrollment>> showAllEnrollments()
    {
        log.info("Started showAllEnrollments");
        return Optional.ofNullable(enrollmentRepository.findAll())
                .map(cour -> new ResponseEntity<>(cour, HttpStatus.OK))
                .orElseThrow(() -> new SpringException("Enrollment DataBase Is Empty"));
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<Student>> showRegisteredStudentsToCourse(@PathVariable( "name" ) String courseName)
    {
        return Optional.ofNullable(serv.showRegisteredStudents(courseName))
                .map(stud -> new ResponseEntity<>(stud, HttpStatus.OK))
                .orElseThrow(() -> new SpringException("No course with the name: " + courseName + " was found"));
    }


}
