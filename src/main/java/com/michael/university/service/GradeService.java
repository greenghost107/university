package com.michael.university.service;

import java.util.Optional;

import com.michael.university.domain.Enrollment;
import com.michael.university.domain.Semester;


public interface GradeService {
	
	public Optional<Enrollment> updateGrade(String courseName, Long studentId, Semester enrollment_semester, Long newGrade);
	
}
