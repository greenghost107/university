package com.michael.university.model;

import java.io.Serializable;



public class EnrollmentId implements Serializable {
    private Long course_id;
    private Long student_id;
    private SEMESTER semester;

    public EnrollmentId(){}

    public EnrollmentId(Long course_id, Long student_id, SEMESTER semester) {
            this.course_id = course_id;
            this.student_id = student_id;
            this.semester = semester;
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

    public SEMESTER getSemester() {
        return semester;
    }

    public void setSemester(SEMESTER semester) {
        this.semester = semester;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EnrollmentId that = (EnrollmentId) o;

        if (!course_id.equals(that.course_id)) return false;
        if (!student_id.equals(that.student_id)) return false;
        return semester == that.semester;

    }

    @Override
    public int hashCode() {
        int result = course_id.hashCode();
        result = 31 * result + student_id.hashCode();
        result = 31 * result + semester.hashCode();
        return result;
    }
}
