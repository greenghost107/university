package com.michael.university.service;

import java.util.ArrayList;
import java.util.Collections;
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
import com.michael.university.repository.CourseRepository;

import net.bytebuddy.asm.Advice.This;

@Service
public class CourseServiceImpl implements CourseService {
	private static final Logger log = LoggerFactory.getLogger(This.class);

	@Autowired
    private CourseRepository courserepository;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private EnrollmentService enrollmentService;
	
	public List<Course> findAllCourses()
    {
        return courserepository.findAll();
    }
	
	@Transactional
    public Optional<Course> addCourse(String courseName, String department_name)
    {
        if(courseName != null && courseName.length() == 0){
            log.error("Coure name is empty");
            return null;
        }
        if (department_name != null && department_name.length() == 0)
        {
            log.error("Department name is empty");
            return null;
        }
        if (!departmentService.isDepartmentExists(department_name))
        {
        	log.error("No Department was found with this name");
        	return null;
        }
//        if (departmentrepository.findByName(department_name).size() ==0){
//            System.out.println("No department was found with this name");
//            return null;
//        }
//        return courserepository.save(new Course(courseName,departmentrepository.findByName(department_name).get(0)));
        Course course = courserepository.save(new Course(courseName,departmentService.findDepartmentByName(department_name).get()));
        return Optional.of(course);

    }
	
	//not working
    @Transactional
    public List<Course> showregisteredCourses(Long studentId)
    {
//        List<Student> student_list = studentRepository.findByName(studentName);
//        if (student_list.size()==0)
//        {
//            System.out.println("No student with the name: " + studentName + " In the database" );
//            return null;
//        }
//        Student student = student_list.get(0);
        
        Optional<Student> optStudent = studentService.findStudentById(studentId);
        if(!optStudent.isPresent()) {
        	log.error("Couldn't find student with Id: " + studentId );
        	return Collections.emptyList();
        }
        Student student = optStudent.get();
        
        List<Enrollment> enrollment_list = enrollmentService.findEnrollmentByStudent(student);
        List<Course> newList = new ArrayList<Course>();
//        System.out.println("Student  " + studentName +  " is registered to: ");
        for (Enrollment enrollment : enrollment_list)
        {
            newList.add(enrollment.getCourse());
//            System.out.println(enrollment.getCourse().getName() + " " + enrollment.getStudent().getName());
        }
        return newList;

    }

	@Override
	public Optional<Course> findCourseByName(String courseName) {
		return courserepository.findByName(courseName);
	}

	@Override
	public Optional<Course> findCourseById(Long courseId) {
		return courserepository.findById(courseId);
	}

	@Override
	public Course deleteCourse(String courseName) {
		Optional<Course> optCourse = courserepository.findByName(courseName);
		if (!optCourse.isPresent())
		{
			log.error("Couldn't find course with name " + courseName);
			return null;
		}
		return optCourse.get();
	}

	@Override
	public Optional<Enrollment> updateGrade(Long courseId, Long studentId, SEMESTER semester, Long grade) {
		EnrollmentId enrollmentId = new EnrollmentId(courseId,studentId,semester);
		return enrollmentService.findEnrollment(enrollmentId);
	}
}
