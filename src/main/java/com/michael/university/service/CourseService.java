package com.michael.university.service;

import java.util.List;
import java.util.Optional;

import com.michael.university.domain.Course;
import com.michael.university.domain.Enrollment;
import com.michael.university.domain.Semester;


public interface CourseService {
	
	public List<Course> findAllCourses();
	
	public Optional<Course> addCourse(String courseName, String department_name);
	
	public Course deleteCourse(String courseName);
	
	public Optional<Course> findCourseById(Long courseId);
	
	public Optional<Course> findCourseByName(String courseName);
	
	public Optional<Enrollment> updateGrade(Long courseId,Long studentId, Semester semester, Long grade);
}
