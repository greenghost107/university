package com.michael.university.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.michael.university.model.Course;
import com.michael.university.model.Enrollment;
import com.michael.university.model.EnrollmentId;
import com.michael.university.model.SEMESTER;
import com.michael.university.model.Student;

import net.bytebuddy.asm.Advice.This;

@Service
public class GradeServiceImpl implements GradeService {
	private static final Logger log = LoggerFactory.getLogger(This.class);
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private EnrollmentService enrollmentService;
	
	@Transactional
	public Optional<Enrollment> updateGrade(String courseName, Long studentId, SEMESTER enrollment_semester, Long newGrade) {
		// Update student grades
		// Long course_id = courserepository.findByName(courseName).get(0).getId();
		Optional<Course> optCourse = courseService.findCourseByName(courseName);
		Long course_id = (optCourse.isPresent() ? courseService.findCourseByName(courseName).get().getId() : -1);
		
		// Long student_id = studentRepository.findByName(studentName).get(0).getId();
		Optional<Student> optStudent = studentService.findStudentById(studentId);
		Long student_id = (optStudent.isPresent() ? optStudent.get().getId(): -1);
		SEMESTER semester = enrollment_semester;
		if (student_id == -1|| course_id == -1 ) {
			log.error("Wasn't able to updateGrade, Couldn't find Student/Course");
			return Optional.empty();
		}
		// Enrollment enrollment = enrollmentRepository.findOne(new
		// EnrollmentId(course_id,student_id,semester));
		Optional<Enrollment> optEnrollment = enrollmentService.findEnrollment(new EnrollmentId(course_id, student_id, semester));
		if (!optEnrollment.isPresent())
		{
			log.error("Couldn't find enrollment for student id:" + studentId + " course name:" + courseName);
			return Optional.empty();
		}
		Enrollment enrollment = optEnrollment.get();
		Course course = enrollment.getCourse();
		Student student = enrollment.getStudent();
		SEMESTER sem = enrollment.getSemester();
		enrollment.setGrade(newGrade);
		log.info("course name: " + course.getName() + " Student name " + student.getName() + " Semester is " + sem + " new Grade is "
				+ enrollment.getGrade());
		
		return Optional.of(enrollment);
	}
	
	@Transactional
	public Boolean updateGradeByEnrollment(EnrollmentId enrollmentId, Long newGrade) {
		// Enrollment enrollment = enrollmentRepository.findOne(enrollmentId);
		Optional<Enrollment> optEnrollment = enrollmentService.findEnrollment(enrollmentId);
		if (!optEnrollment.isPresent())
		{
			log.error("Couldn't find Enrollment");
			return false;
		}
		Enrollment enrollment = optEnrollment.get();
		Course course = enrollment.getCourse();
		Student student = enrollment.getStudent();
		SEMESTER sem = enrollment.getSemester();
		enrollment.setGrade(newGrade);
		System.out.println("course name: " + course.getName() + " Student name " + student.getName() + " Semester is " + sem
				+ " new Grade is " + enrollment.getGrade());
		return true;
	}
	
	@Transactional
	    public Enrollment updateGradeEnrollment(Long studentId, String courseName, SEMESTER semester, Long grade)
	    {
//	        List<Student> student_list = studentRepository.findByName(studentName);
//	        if (student_list.size()<1){
//	            System.out.println("Couldn't find student");
//	            return null;
//	        }
	        Optional<Student> optStudent = studentService.findStudentById(studentId);
	        if (!optStudent.isPresent())
	        {
	        	log.error("Couldn't find student");
	        	return null;
	        }
//	        Student student = student_list.get(0);
	        Student student = optStudent.get();
//	        List<Course> course_list = courserepository.findByName(courseName);
//	        if (course_list.size()<1){
//	            System.out.println("Couldn't find course");
//	            return null;
//	        }
	        Optional<Course> optCourse = courseService.findCourseByName(courseName);
	        if (!optCourse.isPresent())
	        {
	        	log.error("Couldn't find course");
	        	return null;
	        }
	        
//	        Course course = course_list.get(0);
	        Course course = optCourse.get();
	        EnrollmentId enrollmentId = new EnrollmentId(course.getId(),student.getId(),semester);
	        Optional<Enrollment> optEnrollment =  enrollmentService.findEnrollment(enrollmentId);
	        if (!optEnrollment.isPresent())
	        {
	        	log.error("No enrollment found for Student with id: " + studentId + " Course name " + courseName);
	        	return null;
	        }
	        Enrollment enrollment = optEnrollment.get();
	        enrollment.setGrade(grade);
	        return enrollment;

	    }
}
