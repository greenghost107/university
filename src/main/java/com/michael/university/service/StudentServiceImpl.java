package com.michael.university.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.michael.university.domain.Course;
import com.michael.university.domain.Enrollment;
import com.michael.university.domain.SEMESTER;
import com.michael.university.domain.Student;
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
		// List<Student> list_students = studentRepository.findByName(studentName);
		// if (list_students.size()==0)
		// {
		// System.out.println("Couldn't find student with name " + studentName);
		// return null;
		// }
		// return list_students.get(0);
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
		// List<Student> student_list = studentRepository.findByName(studentName);
		// if (student_list.size()==0)
		// {
		// System.out.println("No Student with this name");
		// return null;
		// }
		// Student student = student_list.get(0);
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
//		List<Course> course_list = courserepository.findByName(courseName);
//		if (course_list.size() == 0) {
//			System.out.println("No course with the name: " + courseName + " In the database");
//			return null;
//		}
//		Course course = course_list.get(0);
		if (!optCourse.isPresent())
		{
			log.error("No course with the name: " + courseName + " In the database");
			return null;
		}
		Course course = optCourse.get();
		List<Enrollment> enrollment_list = enrollmentService.findEnrollmentByCourse(course);
		List<Student> newList = new ArrayList<Student>();
		
		// System.out.println("Course " + courseName + " has the following students ");
		for (Enrollment enrollment : enrollment_list) {
			newList.add(enrollment.getStudent());
			// System.out.println(enrollment.getCourse().getName() + " " +
			// enrollment.getStudent().getName());
		}
		
		return newList;
		
	}

	@Override
	public Optional<Enrollment> updateGrade(Long courseId, Long studentId, SEMESTER enrollment_semester, Long newGrade) {
		// TODO Auto-generated method stub
		return null;
	}
}
