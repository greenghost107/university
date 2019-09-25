package com.michael.university.service;

import java.util.List;
import java.util.Optional;

import com.michael.university.domain.Department;

public interface DepartmentService {
	
	public Optional<Department> addDepartment(String departmentName);
	
	public List<Department> findDepartments();
	
	public Long calculateDepartmentAverage(String departmentName);
	
	public boolean isDepartmentExists(String departmentName);
	
	public Optional<Department> findDepartmentByName(String departmentName);
	
}
