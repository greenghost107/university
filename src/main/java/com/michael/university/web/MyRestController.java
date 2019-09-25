//package com.michael.university.web;
//
//import com.example.domain.*;
//import com.example.exceptions.CourseNotFoundException;
//import com.example.exceptions.DepartmentNotFoundException;
//import com.example.exceptions.SpringException;
//import com.example.exceptions.StudentNotFoundException;
//import com.example.repository.DepartmentRepository;
//import com.example.repository.StudentRepository;
//import com.example.service.servicesImpl;
//import com.michael.university.domain.Course;
//import com.michael.university.domain.Enrollment;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import java.util.Optional;
//import java.util.List;
//
//
//
//
//@RestController
//public class MyRestController {
//    private static final String template = "Hello, %s!";
//
//    @Autowired
//    DepartmentRepository departmentrepository;
//
//    @Autowired
//    StudentRepository studentRepository;
//
//    @Autowired
//    private servicesImpl serv;
//
//
//    @RequestMapping("/greeting")
//    public Student greeting(@RequestParam(value="name", defaultValue="World") String name) {
//        return new Student(String.format(template, name));
//    }
//
//    @RequestMapping("/stam")
//    public Student stam(@RequestParam(value="name", defaultValue="Michael") String name) {
//        return new Student(String.format(template, name));
//    }
//
//
//    @RequestMapping(value = "/department/{id}",method = RequestMethod.GET)
//    public ResponseEntity<List<Course>> showCourses(@PathVariable( "id" ) Long departmentId ) {
//        return Optional.ofNullable(departmentrepository.findOne(departmentId))
//                .map(dep -> new ResponseEntity<>(dep.getCourses(), HttpStatus.OK))
//                .orElseThrow(() -> new DepartmentNotFoundException(departmentId));
//    }
//
//    @RequestMapping(value = "{studentName}/{courseName}/{semester}", method = RequestMethod.PUT)
//    public ResponseEntity<Enrollment> updateGrade(@PathVariable("studentName") String studentName, @PathVariable("courseName") String courseName,
//                                                  @PathVariable ("semester") Semester semester, @RequestBody Long grade)
//    {
//        return Optional.ofNullable(serv.updateGradeEnrollment(studentName,courseName,semester,grade))
//                .map(dep -> new ResponseEntity<>(dep, HttpStatus.OK))
//                .orElseThrow(() -> new SpringException("No Enrollment"));
//    }
//
//
//
//
//
//}
//
