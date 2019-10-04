package com.michael.university.service;

import java.util.Optional;

import com.michael.university.model.Enrollment;
import com.michael.university.model.SEMESTER;


public interface GradeService {
	
	public Optional<Enrollment> updateGrade(String courseName, Long studentId, SEMESTER enrollment_semester, Long newGrade);
	
}
