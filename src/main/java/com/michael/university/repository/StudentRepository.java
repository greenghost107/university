package com.michael.university.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.michael.university.domain.Student;

import java.util.List;
import java.util.Optional;


/**
 * Created by Michael on 30/05/2016.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByName(String name);
    Optional<Student> findById(Long id);
}

