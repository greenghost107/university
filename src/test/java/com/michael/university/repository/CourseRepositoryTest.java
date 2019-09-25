package com.michael.university.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.michael.university.domain.Course;
import com.michael.university.domain.Department;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CourseRepositoryTest {


    @Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private CourseRepository courseRepository;
//	
//	@Before
//	public void setUp() throws Exception {
//	}

//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}
	
	@Test
	public void whenFindByName_thenReturnCourse() {
	    // given
	    Course course = new Course("Data Structures",new Department("Computer Science"));
	    entityManager.persist(course);
	    entityManager.flush();
	 
	    // when
	    Optional<Course> found = courseRepository.findByName(course.getName());
	 
	    // then
	    assertEquals(found.isPresent(), true);
	    assertThat(found.get().getName())
	      .isEqualTo(course.getName());
	}

}
