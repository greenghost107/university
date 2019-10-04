package com.michael.university.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.michael.university.model.Course;
import com.michael.university.model.Department;
import com.michael.university.model.Enrollment;
import com.michael.university.model.SEMESTER;
import com.michael.university.model.Student;
import com.michael.university.repository.EnrollmentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EnrollmentServiceImplTest {
	/**
	 * If we need injection of Beans from springrunner but we also need to use @InjectMocks or @Spy
	 * this is Spring runner with the Mockito rule
	 */
	@Rule
    public MockitoRule rule = MockitoJUnit.rule();
	
	
	@TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
  
        @Bean
        public EnrollmentService enrollmentService() {
            return new EnrollmentServiceImpl();
        }
    }
 
    @Autowired
    private EnrollmentService enrollmentService;
 
    @MockBean
    private EnrollmentRepository enrollmentRepository;
    
    @InjectMocks
    private CourseServiceImpl courseService;
    
    @InjectMocks
    private StudentServiceImpl studentService;
    
    private List<String> studentNamesList;
    
    private List<String> courseNameList;
    
    private List<Enrollment> enrollmentList;
    
	@Before
	public void setUp() throws Exception {
		initializeLists();
		//Add more Departments
		Department department = new Department("Sciences");
		MockitoAnnotations.initMocks(this);
		
		for (int i=0 ;i<studentNamesList.size() && i<courseNameList.size();i++)
		{
			Student student = new Student(studentNamesList.get(i));
			Course course = new Course(courseNameList.get(i),department);
			Enrollment enrollment = new Enrollment(student,course,SEMESTER.A,null);
			enrollmentList.add(enrollment);
			List<Enrollment> enrollmentlst = new ArrayList<>();
			enrollmentlst.add(enrollment);
			Mockito.when(enrollmentRepository.findByStudent(student)).thenReturn(enrollmentlst);		
			}
    	
    	Mockito.when(enrollmentRepository.findAll()).thenReturn(enrollmentList);
	
	}
	
	private void initializeLists()
	{
		studentNamesList = new ArrayList<String>();
		studentNamesList.add("Avi");
		studentNamesList.add("Loren");
		studentNamesList.add("Josh");
		courseNameList = new ArrayList<String>();
		courseNameList.add("Physics");
		courseNameList.add("Mathematics");
		enrollmentList = new ArrayList<Enrollment>();
	}
	
	@Test
	public void whenFindAllEnrollments_thenAllEnrollemtnsReturned() {
		List<Enrollment> enrollemtnlst = enrollmentService.findAllEnrollments();
		assertTrue(enrollemtnlst.size()==2);
		int index=0;
		for (Enrollment enrollemnt: enrollemtnlst)
    	{
    		assertEquals(enrollemnt.getClass(),Enrollment.class);
    		assertEquals(enrollemnt.getStudent().getClass(), Student.class);
    		assertEquals(enrollemnt.getStudent().getName(),studentNamesList.get(index));
    		assertEquals(enrollemnt.getCourse().getClass(),Course.class );
    		assertEquals(enrollemnt.getCourse().getName(),courseNameList.get(index));
    		assertEquals(enrollemnt.getSemester().getClass(), SEMESTER.class);
    		index++;
    	}
	}
	
	
	@Test
	public void whenFindEnrollmentByStudent_thenAllEnrollmentsForStudent()
	{
		
		for(Enrollment enrollment : enrollmentList)
		{
			List<Enrollment> enrollmentlst = enrollmentService.findEnrollmentByStudent(enrollment.getStudent());
			assertEquals(enrollmentlst.size(),1);
			assertNotNull(enrollment.getStudent());
			assertNotNull(enrollment.getCourse());
//			assertEquals(enrollmentlst.size(), Math.min(courseNameList.size(),studentNamesList.size()));
		}
		
	}

//	@Test
//	public void testFindEnrollment() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testFindEnrollmentByStudent() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testFindEnrollmentByCourse() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testShowRegisteredStudents() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testRegisterStudentToCourse() {
//		fail("Not yet implemented");
//	}

}
