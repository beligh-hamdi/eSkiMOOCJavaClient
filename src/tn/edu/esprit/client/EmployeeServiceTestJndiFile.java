package tn.edu.esprit.client;

import static org.junit.Assert.*;

import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import tn.edu.esprit.eskimooc.domain.Employee;
import tn.edu.esprit.eskimooc.service.interfaces.EmployeeServiceRemote;

public class EmployeeServiceTestJndiFile {

	
	private  Context context;
	private EmployeeServiceRemote proxy;
	
	
	@Before
	public void setUp() throws Exception {
		
		
		String moduleName = "eSkiMOOC";
		String serviceName = "employee-service";
		
		try {
			context = new InitialContext();	
			String jndiName = "ejb:/"+moduleName+"/"+ serviceName+"!" + EmployeeServiceRemote.class.getCanonicalName();
			proxy = (EmployeeServiceRemote) context.lookup(jndiName);
		
		} catch (NamingException e) {
			e.printStackTrace();
		}
	
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
		
		
	}

}
