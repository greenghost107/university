package com.michael.university.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.michael.university.domain.Course;
import com.michael.university.repository.CourseRepository;

@Service
public class CourseService {
	private static final Logger log = LoggerFactory.getLogger(CourseService.class);

	@Autowired
    CourseRepository courserepository;
	
	public List<Course> findAllCourses()
    {
        return courserepository.findAll();
    }
	
	@Transactional
    public Course addCourse(String courseName, String department_name)
    {
        if(courseName != null && courseName.length() == 0){
            System.out.println("Please enter CourseName!");
            return null;
        }
        if (department_name != null && department_name.length() == 0)
        {
            System.out.println("Please enter department name");
            return null;
        }
        if (departmentrepository.findByName(department_name).size() ==0){
            System.out.println("No department was found with this name");
            return null;
        }
        return courserepository.save(new Course(courseName,departmentrepository.findByName(department_name).get(0)));

    }
	
	//not working
    @Transactional
    public List<Course> showregisteredCourses(String studentName)
    {
        List<Student> student_list = studentRepository.findByName(studentName);
        if (student_list.size()==0)
        {
            System.out.println("No student with the name: " + studentName + " In the database" );
            return null;
        }
        Student student = student_list.get(0);
        List<Enrollment> enrollment_list = enrollmentRepository.findByStudent(student);
        List<Course> newList = new ArrayList<Course>();
//        System.out.println("Student  " + studentName +  " is registered to: ");
        for (Enrollment enrollment : enrollment_list)
        {
            newList.add(enrollment.getCourse());
//            System.out.println(enrollment.getCourse().getName() + " " + enrollment.getStudent().getName());
        }
        return newList;

    }
	
}
