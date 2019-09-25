package com.michael.university.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.michael.university.domain.Course;
import com.michael.university.domain.Enrollment;
import com.michael.university.domain.EnrollmentId;
import com.michael.university.domain.Student;

import java.util.List;


@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment,EnrollmentId> {

    List<Enrollment> findByCourse(Course course);
    List<Enrollment> findByStudent(Student student);



}
