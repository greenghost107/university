package com.michael.university.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.michael.university.domain.AvgCalc;
import com.michael.university.domain.BonusAverage;
import com.michael.university.domain.Course;
import com.michael.university.domain.Department;
import com.michael.university.repository.DepartmentRepository;

import net.bytebuddy.asm.Advice.This;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	private static final Logger log = LoggerFactory.getLogger(This.class);
	
	@Autowired
	DepartmentRepository departmentrepository;
	
	public List<Department> findDepartments() {
		return departmentrepository.findAll();
	}
	
	public Optional<Department> findDepartmentByName(String department_name)
	{
		return departmentrepository.findByName(department_name);
	}
	
	
	@Transactional
	public Long calculateDepartmentAverage(String departmentName) {
		Optional<Department> optDepartment = departmentrepository.findByName(departmentName);
		if (isDepartmentExists(departmentName))
		{
			log.error("No department with this name");
			return null;
		}
		Department department = optDepartment.get();
		
		switch (departmentName) {
			case "Computer Science":
				department.setAvgCalc(new BonusAverage(85, 5, 30, 10));
				break;
			case "Software Engineering":
				department.setAvgCalc(new BonusAverage(80, 5, 50, 10));
				break;
			case "Information Systems":
				department.setAvgCalc(new BonusAverage(70, 10, 40, 15));
				break;
		}
		
		AvgCalc avgCalc = department.getAvgCalc();
		List<Course> course_list = department.getCourses();
		Long ans = avgCalc.calcAverage(course_list);
		
		return ans;
	}
	
	public boolean isDepartmentExists(String departmentName) {
		Optional<Department> optDepartment = departmentrepository.findByName(departmentName);
		return optDepartment.isPresent() ? true : false;
	}

	@Override
	public Optional<Department> addDepartment(String departmentName) {
		Department department = new Department(departmentName);
		return Optional.of(departmentrepository.save(department));
	}
	
}
