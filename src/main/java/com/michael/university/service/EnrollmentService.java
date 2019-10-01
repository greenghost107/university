package com.michael.university.service;

import java.util.List;
import java.util.Optional;

import com.michael.university.domain.Course;
import com.michael.university.domain.Enrollment;
import com.michael.university.domain.EnrollmentId;
import com.michael.university.domain.SEMESTER;
import com.michael.university.domain.Student;

public interface EnrollmentService {
	
	public List<Enrollment> findAllEnrollments();
	
	public Optional<Enrollment> findEnrollment(EnrollmentId enrollmentId);
	
	public List<Enrollment> findEnrollmentByStudent(Student student);
	
	public List<Enrollment> findEnrollmentByCourse(Course course);
	
	public List<Student> showRegisteredStudents(String courseName);
	
	public Optional<Enrollment> registerStudentToCourse(Long studentId, Long courseId, SEMESTER semester);
}
