package com.michael.university.web;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.michael.university.exceptions.SpringException;
import com.michael.university.model.Enrollment;
import com.michael.university.model.EnrollmentId;
import com.michael.university.model.SEMESTER;
import com.michael.university.service.EnrollmentService;

import net.bytebuddy.asm.Advice.This;

/**
 * Created by Michael on 13/06/2016.
 */
@RestController
@RequestMapping("/register")
public class EnrollmentController {
	private static final Logger log = LoggerFactory.getLogger(This.class);
	
	@Autowired
	private EnrollmentService enrollmentService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Enrollment>> showAllEnrollments() {
		log.info("Started showAllEnrollments");
		return Optional.ofNullable(enrollmentService.findAllEnrollments())
				.map(cour -> new ResponseEntity<>(cour, HttpStatus.OK))
				.orElseThrow(() -> new SpringException("Enrollment DataBase Is Empty"));
	}
	
	@RequestMapping(value = "/{courseId}", method = RequestMethod.GET)
	public ResponseEntity<List<Enrollment>> showRegisteredStudentsToCourse(@PathVariable("courseId") Long courseId) {
		return Optional.ofNullable(enrollmentService.showRegisteredStudents(courseId))
				.map(stud -> new ResponseEntity<>(stud, HttpStatus.OK))
				.orElseThrow(() -> new SpringException("No course with the id: " + courseId + " was found"));
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ResponseEntity<Enrollment> registerStudentToCourse(@RequestParam("studentId") Long studentId,
			@RequestParam("courseId") Long courseId, @RequestParam("semester") SEMESTER semester) {
		return enrollmentService.registerStudentToCourse(studentId, courseId, semester)
				.map(enr -> new ResponseEntity<>(enr, HttpStatus.OK))
				.orElseThrow(() -> new SpringException("Couldn't Register student with id: " + studentId + " to course with id: " + courseId
						+ " in semester " + semester));
	}
	
	@RequestMapping(value = "/{courseId}/{studentId}/{semester}", method = RequestMethod.GET)
	public ResponseEntity<Enrollment> findEnrollmentByCourseIdAndStudentIdAndSemester(@PathVariable("courseId") Long courseId,
			@PathVariable("studentId") Long studentId,@PathVariable("semester") SEMESTER semester) {
			return enrollmentService.findEnrollment(new EnrollmentId(courseId,studentId,semester))
					.map(enr -> new ResponseEntity<>(enr, HttpStatus.OK))
					.orElseThrow(() -> new SpringException("Couldn't find Student by id : "+ studentId + " and courseId " + courseId));
	}
}
