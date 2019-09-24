package com.michael.university.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.michael.university.repository.CourseRepository;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

/**
 * Created by Michael on 09/06/2016.
 */
@RestController
@RequestMapping("/courses")
public class CourseController {
    private static final Logger log = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    private servicesImpl serv;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Course>> showAllCourses()
    {
        log.info("Started showAllCourses");
        return Optional.ofNullable(serv.findAllCourses())
                .map(cour -> new ResponseEntity<>(cour, HttpStatus.OK))
                .orElseThrow(() -> new SpringException("Course DataBase Is Empty"));
    }



    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Course> showCourse(@PathVariable( "id" ) Long courseId )
    {
        return Optional.ofNullable(courseRepository.findOne(courseId))
                .map(stud -> new ResponseEntity<>(stud, HttpStatus.OK))
                .orElseThrow(() -> new CourseNotFoundException(courseId));
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Course> addCourse(@RequestParam String courseName,@RequestParam String departmentName)
    {

        return Optional.ofNullable(serv.addCourse(courseName,departmentName))
                .map(stud -> new ResponseEntity<>(stud, HttpStatus.OK))
                .orElseThrow(() -> new SpringException("Can't add" + courseName + " to " + departmentName));

    }

    @RequestMapping(value = "/{name}" , method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteCourse(@PathVariable( "name" ) String courseName )
    {
        log.info("delete course");
        return new ResponseEntity<>(serv.deleteCourse(courseName), HttpStatus.OK);

    }
    @RequestMapping(value = "/update/{name}" ,method = RequestMethod.POST)
    public ResponseEntity<Enrollment> updateStudentGrade(@PathVariable ( "name" )String courseName , @RequestHeader(value = "studentName") String studentName
            , @RequestHeader(value = "semester") Semester semester, @RequestHeader Long grade)
    {
        return Optional.ofNullable(serv.updateGrade(courseName,studentName,semester,grade))
                .map(bool -> new ResponseEntity<>(bool, HttpStatus.OK))
                .orElseThrow(() -> new SpringException("couldn't update grade"));
    }


}
