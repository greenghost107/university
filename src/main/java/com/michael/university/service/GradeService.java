package com.michael.university.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GradeService {
	private static final Logger log = LoggerFactory.getLogger(GradeService.class);
	
	
	@Transactional
    public Enrollment updateGrade(String courseName , String studentName, Semester enrollment_semester, Long newGrade)
    {
        //Update student grades
        Long course_id = courserepository.findByName(courseName).get(0).getId();
        Long student_id = studentRepository.findByName(studentName).get(0).getId();
        Semester semester = enrollment_semester;
        Enrollment enrollment = enrollmentRepository.findOne(new EnrollmentId(course_id,student_id,semester));
        Course course = enrollment.getCourse();
        Student student = enrollment.getStudent();
        Semester sem = enrollment.getSemester();
        enrollment.setGrade(newGrade);
        System.out.println("course name: " + course.getName() + " Student name " + student.getName() + " Semester is " + sem + " new Grade is " + enrollment.getGrade());

        return enrollment;
    }
	
	 @Transactional
	    public Boolean updateGradeByEnrollment(EnrollmentId enrollmentId,Long newGrade){
	        Enrollment enrollment = enrollmentRepository.findOne(enrollmentId);
	        Course course = enrollment.getCourse();
	        Student student = enrollment.getStudent();
	        Semester sem = enrollment.getSemester();
	        enrollment.setGrade(newGrade);
	        System.out.println("course name: " + course.getName() + " Student name " + student.getName() + " Semester is " + sem + " new Grade is " + enrollment.getGrade());
	        return true;
	    }
	 
	 
	 @Transactional
	    public Enrollment updateGradeEnrollment(String studentName, String courseName, Semester semester, Long grade)
	    {
	        List<Student> student_list = studentRepository.findByName(studentName);
	        if (student_list.size()<1){
	            System.out.println("Couldn't find student");
	            return null;
	        }
	        Student student = student_list.get(0);
	        List<Course> course_list = courserepository.findByName(courseName);
	        if (course_list.size()<1){
	            System.out.println("Couldn't find course");
	            return null;
	        }
	        Course course = course_list.get(0);
	        EnrollmentId enrollmentId = new EnrollmentId(course.getId(),student.getId(),semester);
	        Enrollment enrollment =  enrollmentRepository.findOne(enrollmentId);
	        enrollment.setGrade(grade);
	        return enrollment;

	    }
	
	
}
