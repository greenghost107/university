package com.michael.university.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.michael.university.domain.Enrollment;
import com.michael.university.domain.SEMESTER;
import com.michael.university.domain.Student;
import com.michael.university.exceptions.SpringException;
import com.michael.university.exceptions.StudentNotFoundException;
import com.michael.university.service.StudentService;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/students")
public class StudentController {
	private static final Logger log = LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	private StudentService studentService;
	
	public StudentController() {}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Student>> showAllStudents() {
		return Optional.ofNullable(studentService.findAllStudents())
				.map(stud -> new ResponseEntity<>(stud, HttpStatus.OK))
				.orElseThrow(() -> new SpringException("Students DataBase Is Empty"));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Student> addStudent(
			@ApiParam(name = "addStudent", value = "Student is added to the database", required = true) @Valid @RequestBody Student student) {
		return Optional.ofNullable(studentService.addStudent(student))
				.map(stud -> new ResponseEntity<>(stud, HttpStatus.OK))
				.orElseThrow(() -> new SpringException("Can't add Student"));
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Student> showStudentByID(@PathVariable("id") Long studentId) {
		return studentService.findStudentById(studentId)
				.map(stud -> new ResponseEntity<>(stud, HttpStatus.OK))
				.orElseThrow(() -> new StudentNotFoundException(studentId));
	}
	
	@RequestMapping(value = "/{name}", method = RequestMethod.POST)
	public ResponseEntity<Student> addStudentByName(@PathVariable("name") String studentName) {
		return new ResponseEntity<>(studentService.addStudentByName(studentName), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
	public ResponseEntity<Student> deleteStudent(@PathVariable("name") Long studentId) {
		log.info("delete student");
		return new ResponseEntity<>(studentService.deleteStudentById(studentId), HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/{studentId}/{courseId}/{semester}", method = RequestMethod.POST)
	public ResponseEntity<Enrollment> updateStudentGrade(@PathVariable("studentId") Long studentId,
			@PathVariable(value = "courseId") Long courseId, @PathVariable(value = "semester") SEMESTER semester,
			@RequestBody Long grade) {
		return studentService.updateGrade(courseId, studentId, semester, grade)
				.map(bool -> new ResponseEntity<>(bool, HttpStatus.OK))
				.orElseThrow(() -> new SpringException("couldn't update grade"));
	}
	
}
