package com.michael.university.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.michael.university.model.Course;

import java.util.List;
import java.util.Optional;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByName(String courseName);
    Optional<Course> findById(Long id);

}
