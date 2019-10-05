package com.michael.university.service;

import java.util.ArrayList;
import java.util.List;
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
import com.michael.university.repository.StudentRepository;

import net.bytebuddy.asm.Advice.This;

@Service
public class StudentServiceImpl implements StudentService {
	
	private static final Logger log = LoggerFactory.getLogger(This.class);
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private EnrollmentService enrollmentService;
	
	@Autowired
	private GradeService gradeService;
	
	
	public List<Student> findAllStudents() {
		log.trace("Started findAllStudents");
		return studentRepository.findAll();
	}
	
	@Transactional
	public List<Student> findStudentByName(String studentName) {
		return studentRepository.findByName(studentName);
		
	}
	
	@Transactional
	public Optional<Student> findStudentById(Long studentId) {
		// check that number is positive
		return studentRepository.findById(studentId);
	}
	
	@Transactional
	public Student addStudentByName(String studentName) {
		if (studentName == null) {
			System.out.println("Please enter StudentName!");
			return null;
		}
		if (!isAlpha(studentName)) {
			System.out.println("Please enter Only letters");
			return null;
		}
		return studentRepository.save(new Student(studentName));
		
	}
	
	@Transactional
	public Student addStudent(Student student) {
		return studentRepository.save(student);
	}
	
	private boolean isAlpha(String name) {
		return name.matches("[a-zA-Z]+");
	}
	
	@Transactional
	public Student deleteStudentById(Long studentId) {
		Optional<Student> optStudent = studentRepository.findById(studentId);
		if (!optStudent.isPresent()) {
			log.error("No Student with this name");
			return null;
		}
		Student student = optStudent.get();
		studentRepository.delete(student);
		return student;
		
	}
	
	@Transactional
	public List<Student> showRegisteredStudents(String courseName) {
		Optional<Course> optCourse = courseService.findCourseByName(courseName);
		if (!optCourse.isPresent())
		{
			log.error("No course with the name: " + courseName + " In the database");
			return null;
		}
		Course course = optCourse.get();
		List<Enrollment> enrollment_list = enrollmentService.findEnrollmentByCourse(course);
		List<Student> newList = new ArrayList<Student>();
		
		for (Enrollment enrollment : enrollment_list) {
			newList.add(enrollment.getStudent());
		}
		
		return newList;
		
	}

	@Override
	public Optional<Enrollment> updateGrade(Long courseId, Long studentId, SEMESTER semester, Long newGrade) {
		EnrollmentId enrollmentId = new EnrollmentId(courseId, studentId, semester);
		Optional<Enrollment> optEnrollment = enrollmentService.findEnrollment(enrollmentId);
		if (!optEnrollment.isPresent())
		{
			log.error("Couldn't find enrollment for the specified studentId: " + studentId + " courseId: " + courseId +" semester: " + semester);
			return optEnrollment;
		}
		return Optional.of(gradeService.updateGradeByEnrollment(optEnrollment.get(), newGrade));
	}

	
}
