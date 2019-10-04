package com.michael.university.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.michael.university.model.Student;

import java.util.List;
import java.util.Optional;



@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	List<Student> findByName(String name);
    Optional<Student> findById(Long id);
}

