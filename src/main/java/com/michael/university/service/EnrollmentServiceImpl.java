package com.michael.university.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michael.university.model.Course;
import com.michael.university.model.Enrollment;
import com.michael.university.model.EnrollmentId;
import com.michael.university.model.SEMESTER;
import com.michael.university.model.Student;
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
		return enrollmentRepository.findByCourse(course);
	}
	
	@Override
	public List<Student> showRegisteredStudentsToCourse(Long courseId) {
		Optional<Course> optCourse = courseService.findCourseById(courseId);
		if (!optCourse.isPresent())
		{
			log.error("Couldn't find course with id: " + courseId);
			return Collections.emptyList();
		}
		List<Enrollment> enrollmentList = findEnrollmentByCourse(optCourse.get());
		
		if (enrollmentList.isEmpty())
		{
			log.error("Couldn't Enrollments for course: " + courseId);
			return Collections.emptyList();
		}
		List<Student> returnedStudentList = new ArrayList<>();
		for(Enrollment enrollment:enrollmentList)
		{
			returnedStudentList.add(enrollment.getStudent());
		}
		return returnedStudentList;
		
	}
	
	@Override
	public Optional<Enrollment> registerStudentToCourse(Long studentId, Long courseId, SEMESTER semester) {
		Optional<Student> optStudent = studentService.findStudentById(studentId);
		Optional<Course> optCourse = courseService.findCourseById(courseId);
		if (!optStudent.isPresent() || !optCourse.isPresent()) {
			log.error("Couldn't find Student with id: " + studentId + " or Course with Id: " + courseId);
			return null;
		}
		
		Enrollment enrollment = new Enrollment(optStudent.get(), optCourse.get(), semester, null);
		enrollment = saveEnrollment(enrollment);
		return Optional.of(enrollment);
		
	}
	
	@Override
	public Enrollment updateEnrollmentGrade(Enrollment enrollment)
	{
		return saveEnrollment(enrollment);
	}
	
	private Enrollment saveEnrollment(Enrollment enrollment)
	{
		return enrollmentRepository.save(enrollment);
	}

//	@Override
//	public Optional<Enrollment> findEnrollmentByCourseIdAndStudentId(Long studentId, Long courseId, SEMESTER semester) {
////		Optional<Student> optStudent = studentService.findStudentById(studentId);
////		if (!optStudent.isPresent())
////		{
////			log.error("Couldn't find student with id " + studentId);
////			return Optional.empty();
////		}
////		List<Enrollment> enrollmentList = findEnrollmentByStudent(optStudent.get());
////		Optional<Enrollment> returnEnrollment = Optional.empty();
////		for (Enrollment enrollment:enrollmentList)
////		{
////			if (enrollment.getSemester().equals(semester))
////			{
////				returnEnrollment = Optional.of(enrollment);
////				break;
////			}
////		}
////		return returnEnrollment;
//		EnrollmentId enrollmentId = new EnrollmentId(courseId,studentId,semester);
//		return findEnrollment(enrollmentId);
//	
//	}
	
}
