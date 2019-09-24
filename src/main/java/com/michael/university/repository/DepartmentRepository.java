package com.michael.university.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.michael.university.domain.Department;

import java.util.List;
import java.util.Optional;


/**
 * Created by Michael on 29/05/2016.
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    List<Department> findByName(String depName);
    Optional<Department> findById(Long id);
}
