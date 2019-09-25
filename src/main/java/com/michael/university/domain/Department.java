package com.michael.university.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@courses")
public class Department {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonBackReference
    @OneToMany(mappedBy="dep_id",cascade = CascadeType.ALL)
    private List<Course> courses;

    @Transient
    private AvgCalc avgCalc;


    public Department() {}

    public Department(String depName ) {
        this.name = depName;
        this.courses = new ArrayList<>();

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

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }


    public AvgCalc getAvgCalc() {
        return avgCalc;
    }

    public void setAvgCalc(AvgCalc avgCalc) {
        this.avgCalc = avgCalc;
    }
}
