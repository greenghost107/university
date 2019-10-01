package com.michael.university.service;

import java.util.Optional;

import com.michael.university.domain.Enrollment;
import com.michael.university.domain.SEMESTER;


public interface GradeService {
	
	public Optional<Enrollment> updateGrade(String courseName, Long studentId, SEMESTER enrollment_semester, Long newGrade);
	
}
