package com.michael.university.repository;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.michael.university.model.Department;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(locations="classpath:application-test.properties")
public class DepartmentRepositoryTest {


    @Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private DepartmentRepository departmentRepository;
	
	
	@Before
	public void setUp() throws Exception {
	}

//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}
	
	
	@Test
	public void whenFindByName_thenReturnDepartment() {
	    // given
		Department department = new Department("Physics");
	    entityManager.persist(department);
	    entityManager.flush();
	 
	    // when
	    Optional<Department> found = departmentRepository.findByName(department.getName());
	 
	    // then
	    assertTrue(found.isPresent());
	   assertEquals(department.getName(), found.get().getName());
	}
	
	
	@Test
	public void whenFindById_theReturnDepartment() {
		// given
				Department department = new Department("Computer Science");
			    entityManager.persist(department);
			    entityManager.flush();
			 
			    // when
			    Optional<Department> found = departmentRepository.findById(department.getId());
			 
			    // then
			    assertTrue(found.isPresent());
			   assertEquals(department.getId(), found.get().getId());
	}

}
