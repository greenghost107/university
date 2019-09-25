package com.michael.university.service;

import java.util.List;
import java.util.Optional;

import com.michael.university.domain.Enrollment;
import com.michael.university.domain.Semester;
import com.michael.university.domain.Student;

public interface StudentService {
	
	public List<Student> findAllStudents();
	
	public Student addStudent(Student student);
	
	public Student addStudentByName(String studentName);
	
	public Optional<Student> findStudentById(Long studentId);
	
	public List<Student> findStudentByName(String studentName);
	
	public Student deleteStudentById(Long studentId);
	
	public Optional<Enrollment> updateGrade(Long courseId, Long studentId, Semester enrollment_semester, Long newGrade);
}
