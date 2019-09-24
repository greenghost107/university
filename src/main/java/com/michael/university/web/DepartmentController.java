package com.example.web.rest;

import com.example.domain.Department;
import com.example.domain.Enrollment;
import com.example.exceptions.SpringException;
import com.example.repository.DepartmentRepository;
import com.example.repository.EnrollmentRepository;
import com.example.service.servicesImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Created by Michael on 13/06/2016.
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {
    private static final Logger log = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    private servicesImpl serv;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Department>> showAllDepartments()
    {
        log.info("Started showAllDepartments");
        return Optional.ofNullable(serv.findDepartments())
                .map(cour -> new ResponseEntity<>(cour, HttpStatus.OK))
                .orElseThrow(() -> new SpringException("Enrollment DataBase Is Empty"));
    }


//ask Roi why this method must be Post


    @RequestMapping(value = "/{name}", method = RequestMethod.POST)
    public ResponseEntity<Long> calcAverageForDep(@PathVariable( "name" )String depName)
    {
        return Optional.ofNullable(serv.calculateDepartmentAverage(depName))
                .map(grade -> new ResponseEntity<>(grade, HttpStatus.OK))
                .orElseThrow(() -> new SpringException("Couldn't calculate average for department"));
    }


}
