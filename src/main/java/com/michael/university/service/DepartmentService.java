package com.michael.university.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.michael.university.domain.Department;
import com.michael.university.repository.DepartmentRepository;

@Service
public class DepartmentService {
	private static final Logger log = LoggerFactory.getLogger(DepartmentService.class);
	@Autowired
    DepartmentRepository departmentrepository;
	
	 public List<Department> findDepartments()
	    {
	        return departmentrepository.findAll();
	    }
	 
	 
	  @Transactional
	    public Long calculateDepartmentAverage(String departmentName)
	    {
	        List<Department> department_list = departmentrepository.findByName(departmentName);
	        if (department_list.size()==0){
	            System.out.println("No department with this name");
	            return null;
	        }

	        Department department = department_list.get(0);

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
	
}
