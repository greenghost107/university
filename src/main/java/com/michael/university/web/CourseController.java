package com.michael.university.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.michael.university.domain.Course;
import com.michael.university.domain.Enrollment;
import com.michael.university.domain.SEMESTER;
import com.michael.university.exceptions.CourseNotFoundException;
import com.michael.university.exceptions.SpringException;
import com.michael.university.repository.CourseRepository;
import com.michael.university.service.CourseService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/courses")
public class CourseController {
    private static final Logger log = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Course>> showAllCourses()
    {
        log.info("Started showAllCourses");
        return Optional.ofNullable(courseService.findAllCourses())
                .map(cour -> new ResponseEntity<>(cour, HttpStatus.OK))
                .orElseThrow(() -> new SpringException("Course DataBase Is Empty"));
    }



    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Course> showCourse(@PathVariable( "id" ) Long courseId )
    {
        return courseService.findCourseById(courseId)
                .map(stud -> new ResponseEntity<>(stud, HttpStatus.OK))
                .orElseThrow(() -> new CourseNotFoundException(courseId));
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Course> addCourse(@RequestParam String courseName,@RequestParam String departmentName)
    {

        return courseService.addCourse(courseName,departmentName)
                .map(stud -> new ResponseEntity<>(stud, HttpStatus.OK))
                .orElseThrow(() -> new SpringException("Can't add" + courseName + " to " + departmentName));

    }

    @RequestMapping(value = "/{name}" , method = RequestMethod.DELETE)
    public ResponseEntity<Course> deleteCourse(@PathVariable( "name" ) String courseName )
    {
        log.info("delete course");
        return new ResponseEntity<>(courseService.deleteCourse(courseName), HttpStatus.OK);

    }
    @RequestMapping(value = "/update/{name}" ,method = RequestMethod.POST)
    public ResponseEntity<Enrollment> updateStudentGrade(@PathVariable ( "courseId" )Long courseId , @RequestHeader(value = "studentId") Long studentId
            , @RequestHeader(value = "semester") SEMESTER semester, @RequestHeader Long grade)
    {
        return courseService.updateGrade(courseId,studentId,semester,grade)
                .map(bool -> new ResponseEntity<>(bool, HttpStatus.OK))
                .orElseThrow(() -> new SpringException("couldn't update grade"));
    }


}
