package com.michael.university.service;

import java.util.List;
import java.util.Optional;

import com.michael.university.model.Course;
import com.michael.university.model.Enrollment;
import com.michael.university.model.EnrollmentId;
import com.michael.university.model.SEMESTER;
import com.michael.university.model.Student;

public interface EnrollmentService {
	
	public List<Enrollment> findAllEnrollments();
	
	public Optional<Enrollment> findEnrollment(EnrollmentId enrollmentId);
	
	public List<Enrollment> findEnrollmentByStudent(Student student);
	
	public List<Enrollment> findEnrollmentByCourse(Course course);
	
	public List<Enrollment> showRegisteredStudents(Long courseId);
	
	public Optional<Enrollment> registerStudentToCourse(Long studentId, Long courseId, SEMESTER semester);
	
//	public Optional<Enrollment> findEnrollmentByCourseIdAndStudentId(Long studentId, Long courseId, SEMESTER semester);
}
