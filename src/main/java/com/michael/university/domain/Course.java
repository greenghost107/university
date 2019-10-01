package com.michael.university.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@dep_id")
public class Course {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;


    @Size(min = 4)
    private String name;

    @JsonBackReference
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="dep_id")
    private Department dep_id;

    @JsonBackReference
    @OneToMany(mappedBy="course",targetEntity = Enrollment.class,cascade = CascadeType.REMOVE)
    private List<Enrollment> enrollments;

    public Course() {}

    //Precondition!! Department was chosen correctly in the domain
    //Note! what if department is null???
    public Course(String courseName, Department department)
    {
        this.name = courseName;
        this.dep_id = department;
        this.enrollments=new ArrayList<>();

    }


    public Department getDep_id() {
        return dep_id;
    }

    public void setDep_id(Department dep_id) {
        this.dep_id = dep_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
}
