package tn.edu.esprit.client;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;



import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;

import tn.edu.esprit.eskimooc.domain.Employee;
import tn.edu.esprit.eskimooc.service.interfaces.EmployeeServiceRemote;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeServiceTestLocator {


	private EmployeeServiceRemote proxy;
	
	@Before
	public void setUp() throws Exception {
		
		String moduleName = "eSkiMOOC";
		String serviceName = "employee-service";
	
		proxy = (EmployeeServiceRemote) 
				Locator.lookup(moduleName, serviceName, EmployeeServiceRemote.class);

	}

	@Test
	public void test01_addEmployee() {
		
		Employee employee= new Employee();
		employee.setCin(11111111);
		employee.setFirstname("Beligh");
		employee.setLastname("HAMDI");
		employee.setAddress("Tunisia");
		employee.setTelephone("+216 97 000 568");
		employee.setHiring_date(new Date());
		
		assertTrue(proxy.insert(employee));
		
		
		Employee employee2= new Employee();
		employee2.setCin(22222222);
		employee2.setFirstname("Larry");
		employee2.setLastname("PAGE");
		employee2.setAddress("USA");
		employee2.setTelephone("+1 111 222 333");
		employee2.setHiring_date(new Date());
		
		assertTrue(proxy.insert(employee2));
		
		
	}
	
	@Test
	public void test02_findEmployeeById() {
			
		assertNotNull(proxy.findById(1));

		
		System.out.println("- findEmployeeById()");
		System.out.println("Firstname:"+ proxy.findById(1).getFirstname());
		
	}
	
	
	@Test
	public void test03_updateEmployee() {
			
		Employee employee = proxy.findById(1);
		employee.setAddress("USA");
		
		assertTrue(proxy.update(employee));
		
		System.out.println("- updateEmployee()");
		System.out.println("Firstname:"+ proxy.findById(1).getFirstname());
		System.out.println("New Address:"+ proxy.findById(1).getAddress());
		
	}
	
	@Test
	public void test04_findAllEmployees() {
					
		assertNotNull(proxy.findAll());
		
		System.out.println("findAllEmployees");
		List<Employee> employees = new ArrayList<Employee>();
		employees = proxy.findAll();
		for(Employee e : employees){
			System.out.println("Firstname:"+ e.getFirstname()+ " - Lastname:"+ e.getLastname());
		}
			
	}
	
	
	@Test
	public void test05_findEmployeesWithAddressParam() {
					
		assertNotNull(proxy.findEmployeesWithAddressParam("USA"));
		
		List<Employee> employees = new ArrayList<Employee>();
		employees = proxy.findEmployeesWithAddressParam("USA");
		
		System.out.println("- findEmployeesWithAddressParam(USA)");
		for(Employee e : employees){
			System.out.println("Firstname:"+ e.getFirstname()+ " - Lastname:"+ e.getLastname());
		}
			
	}
	
	
	@Test
	public void test06_deleteEmployee() {
			
		Employee employee = proxy.findById(1);

		assertTrue(proxy.delete(employee));
	
	}
	
	
	

}
