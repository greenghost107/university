package com.michael.university.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.michael.university.exceptions.SpringException;
import com.michael.university.model.Course;
import com.michael.university.model.Department;
import com.michael.university.service.DepartmentService;

import net.bytebuddy.asm.Advice.This;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/department")
public class DepartmentController {
    private static final Logger log = LoggerFactory.getLogger(This.class);


    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Department>> showAllDepartments()
    {
        log.info("Started showAllDepartments");
        return Optional.ofNullable(departmentService.findDepartments())
                .map(cour -> new ResponseEntity<>(cour, HttpStatus.OK))
                .orElseThrow(() -> new SpringException("Enrollment DataBase Is Empty"));
    }



    @RequestMapping(value = "/{name}", method = RequestMethod.POST)
    public ResponseEntity<Long> calcAverageForDep(@PathVariable( "name" )String depName)
    {
        return Optional.ofNullable(departmentService.calculateDepartmentAverage(depName))
                .map(grade -> new ResponseEntity<>(grade, HttpStatus.OK))
                .orElseThrow(() -> new SpringException("Couldn't calculate average for department"));
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Department> addDepartment(@RequestParam String departmentName)
    {

        return departmentService.addDepartment(departmentName)
                .map(stud -> new ResponseEntity<>(stud, HttpStatus.OK))
                .orElseThrow(() -> new SpringException("Can't add" + departmentName));

    }


}
