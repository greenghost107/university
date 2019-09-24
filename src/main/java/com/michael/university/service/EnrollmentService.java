package com.michael.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michael.university.domain.Enrollment;
import com.michael.university.repository.EnrollmentRepository;

@Service
public class EnrollmentService {

	 @Autowired
	    EnrollmentRepository enrollmentRepository;
	
	    public List<Enrollment> findAllEnrollments()
	    {
	        return enrollmentRepository.findAll();
	    }
	 
}
