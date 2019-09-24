package com.michael.university.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.michael.university.domain.Course;

import java.util.List;
import java.util.Optional;

/**
 * Created by Michael on 30/05/2016.
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByName(String courseName);
    Optional<Course> findById(Long id);

}
