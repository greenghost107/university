package com.michael.university.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.michael.university.model.Department;
import com.michael.university.repository.DepartmentRepository;



@RunWith(SpringRunner.class)
public class DepartmentServiceImplTest {
	

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
  
        @Bean
        public DepartmentService departmentService() {
            return new DepartmentServiceImpl();
        }
    }
 
    @Autowired
    private DepartmentService departmentService;
 
    @MockBean
    private DepartmentRepository departmentRepository;
	
    @Before
    public void setUp() {
//        Department computer_science = new Department("Computer Science");
//        Department life_Sciences = new Department("Life Sciences");
//        Department chemistry = new Department("Chemistry");
    	List<Department> depList = new ArrayList<>();
    	depList.add(new Department("Computer Science"));
    	depList.add(new Department("Life Sciences"));
    	depList.add(new Department("Chemistry"));
//        HashMap<String, Department> dep_hashmap = new HashMap<>();
//        dep_hashmap.put("Computer Science", new Department("Computer Science"));
//        dep_hashmap.put("Life Sciences", new Department("Life Sciences"));
//        dep_hashmap.put("Chemistry", new Department("Chemistry"));
        for(Department dep:depList)
        {
        	 Mockito.when(departmentRepository.findByName(dep.getName()))
             .thenReturn(Optional.of(dep));
        }
        Mockito.when(departmentRepository.findAll()).thenReturn(depList);
        
        
        Department testDep = new Department("Test Department");
        Mockito.when(departmentRepository.save(Mockito.any(Department.class))).thenReturn(testDep);
//        Mockito.when(departmentRepository.findByName(computer_science.getName()))
//          .thenReturn(Optional.of(computer_science));
    }
    
    @Test
    public void whenValidName_thenDepartmentShouldBeFound() {
        String dep_name = "Computer Science";
        Optional<Department> found = departmentService.findDepartmentByName(dep_name);
      
        assertTrue(found.isPresent());
        
         assertThat(found.get().getName())
          .isEqualTo(dep_name);
     }
    
    
    @Test
    public void whenFindAllDepartments_thenAllDepartmentsReturned() {
    	List<Department> depList = departmentService.findDepartments();
    	assertTrue(depList.size()==3);
    	for (Department dep: depList)
    	{
    		assertEquals(dep.getClass(),Department.class);
    	}
    }
    	
    	
    @Test
    public void whenIsDepartmentExist_thenReturnBooleanIfYes() {
    	assertTrue(departmentService.isDepartmentExists("Computer Science"));
    	assertFalse(departmentService.isDepartmentExists("Dancing"));
    }
    
    @Test
    public void whenAddingNewDepartment_thenGetOptionalDepartmentWithId() {
    	Optional<Department> optDep = departmentService.addDepartment("Test Department");
    	assertTrue(optDep.isPresent());
    	assertEquals(optDep.get().getName(),"Test Department");
    }
    	
    
    
    
    
    
    
}
