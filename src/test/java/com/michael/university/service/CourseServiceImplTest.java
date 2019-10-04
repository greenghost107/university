//package com.michael.university.service;
//
//import static org.junit.Assert.assertEquals;
//
//import java.util.Optional;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.michael.university.config.H2TestProfileJpaConfig;
//import com.michael.university.domain.Course;
//import com.michael.university.domain.Department;
//import com.michael.university.repository.CourseRepository;
//
//@RunWith(SpringRunner.class)
//@ActiveProfiles("test")
//@DataJpaTest
//public class CourseServiceImplTest {
//	
//	@Autowired
//	private TestEntityManager entityManager;
//	
//	@Autowired
//    private CourseRepository courseRepository;
//	
////	@Test
////	public void test() {
////		fail("Not yet implemented");
////	}
//	
//	@Test
//	public void whenFindByName_thenReturnEmployee() {
//	    // given
//		Course course = new Course("Data Structures",new Department("Computer Science"));
//	    
//	    entityManager.persist(course);
//	    entityManager.flush();
//	 
//	    // when
//	    Optional<Course> found = courseRepository.findByName(course.getName());
//	 
//	    // then
//	    assertEquals(found.isPresent(), true);
//	    assertEquals(course.getName(), found.get().getName());
//	    
////	    assertThat(found.getName())
////	      .isEqualTo(alex.getName());
//	}
//	
//}
