package com.michael.university.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


@Entity
@IdClass(EnrollmentId.class)
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@student_id" )
@Table(name="enrollment") 
public class Enrollment {

    @Id
    private Long course_id;

    @Id
    private Long student_id;

    @Id
    @Enumerated(EnumType.STRING)
    private SEMESTER semester;

    //@Id
    @JsonBackReference
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="course_id",insertable = false, updatable = false)
    private Course course;

    //@Id
    @JsonBackReference
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="student_id",insertable = false, updatable = false)
    private Student student;

    @Max(100)
    @Min(0)
    private Long grade;




    public Enrollment() {}

    public Enrollment(Student student, Course course, SEMESTER semester,Long grade)
    {
        this.student_id = student.getId();
        this.course_id = course.getId();
        this.student = student;
        this.course = course;
        this.semester = semester;
        this.grade = grade;

    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public SEMESTER getSemester() {
        return semester;
    }

    public void setSemester(SEMESTER semester) {
        this.semester = semester;
    }

    public Long getGrade() {
        return grade;
    }

    public void setGrade(Long grade) {
        this.grade = grade;
    }
}