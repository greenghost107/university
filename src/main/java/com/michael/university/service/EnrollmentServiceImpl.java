package com.michael.university.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michael.university.domain.Course;
import com.michael.university.domain.Enrollment;
import com.michael.university.domain.EnrollmentId;
import com.michael.university.domain.SEMESTER;
import com.michael.university.domain.Student;
import com.michael.university.repository.EnrollmentRepository;

import net.bytebuddy.asm.Advice.This;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
	private static final Logger log = LoggerFactory.getLogger(This.class);
	
	@Autowired
	private EnrollmentRepository enrollmentRepository;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private StudentService studentService;
	
	public List<Enrollment> findAllEnrollments() {
		return enrollmentRepository.findAll();
	}
	
	@Override
	public Optional<Enrollment> findEnrollment(EnrollmentId enrollmentId) {
		return enrollmentRepository.findById(enrollmentId);
	}
	
	@Override
	public List<Enrollment> findEnrollmentByStudent(Student student) {
		return enrollmentRepository.findByStudent(student);
	}
	
	@Override
	public List<Enrollment> findEnrollmentByCourse(Course course) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Student> showRegisteredStudents(String courseName) {
		// TODO Auto-generated method stub
				return null;
	}

	@Override
	public Optional<Enrollment> registerStudentToCourse(Long studentId, Long courseId, SEMESTER semester) {
		Optional<Student> optStudent = studentService.findStudentById(studentId);
		Optional<Course> optCourse = courseService.findCourseById(courseId);
		if (!optStudent.isPresent() || !optCourse.isPresent()) {
			log.error("Couldn't find Student with id: " + studentId + " or Course with Id: " + courseId);
			return null;
		}
		
		Enrollment enrollment = new Enrollment(optStudent.get(), optCourse.get(), semester, 56L);
		enrollment = enrollmentRepository.save(enrollment);
		return Optional.of(enrollment);
		
		
	}
	
	
}
