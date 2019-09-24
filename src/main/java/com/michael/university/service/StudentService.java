package com.michael.university.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.michael.university.domain.Student;
import com.michael.university.repository.StudentRepository;

@Service
public class StudentService {
	private static final Logger log = LoggerFactory.getLogger(StudentService.class);
	
	 @Autowired
	    StudentRepository studentRepository;
	 
	 public List<Student> findAllStudents()
	    {
	        log.trace("Started findAllStudents");
	        return studentRepository.findAll();
	    }
	 
	  @Transactional
	    public Student findStudentByName(String studentName)
	    {
	        List<Student> list_students = studentRepository.findByName(studentName);
	        if (list_students.size()==0)
	        {
	            System.out.println("Couldn't find student with name " + studentName);
	            return null;
	        }
	        return list_students.get(0);
	    }
	  
	    @Transactional
	    public Student findStudentById(Long studentId){
	        //check that number is positive
	        return studentRepository.findOne(studentId);
	    }
	    
	    @Transactional
	    public Student addStudentByName(String studentName)
	    {
	        if (studentName==null){
	            System.out.println("Please enter StudentName!");
	            return null;
	        }
	        if (!isAlpha(studentName))
	        {
	            System.out.println("Please enter Only letters");
	            return null;
	        }
	        return studentRepository.save(new Student(studentName));

	    }

	    @Transactional
	    public Student addStudent(Student student)
	    {
	        return studentRepository.save(student);
	    }



	    private boolean isAlpha(String name) {
	        return name.matches("[a-zA-Z]+");
	    }
	    
	    @Transactional
	    public Student deleteStudent(String studentName)
	    {
	        List<Student> student_list = studentRepository.findByName(studentName);
	        if (student_list.size()==0)
	        {
	            System.out.println("No Student with this name");
	            return null;
	        }
	        Student student = student_list.get(0);
	        studentRepository.delete(student);
	        return student;


	    }
	    
	    
	    @Transactional
	    public List<Student> showRegisteredStudents(String courseName)
	    {
	        List<Course> course_list = courserepository.findByName(courseName);
	        if (course_list.size()==0){
	            System.out.println("No course with the name: " + courseName + " In the database" );
	            return null;
	        }
	        Course course = course_list.get(0);
	        List<Enrollment> enrollment_list = enrollmentRepository.findByCourse(course);
	        List<Student> newList = new ArrayList<Student>();

//	        System.out.println("Course  " + courseName +  " has the following students ");
	        for (Enrollment enrollment : enrollment_list)
	        {
	            newList.add(enrollment.getStudent());
//	            System.out.println(enrollment.getCourse().getName() + " " + enrollment.getStudent().getName());
	        }

	        return newList;


	    }
	    
	 
}
