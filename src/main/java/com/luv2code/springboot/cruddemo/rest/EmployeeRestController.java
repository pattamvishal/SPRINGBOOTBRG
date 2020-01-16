package com.luv2code.springboot.cruddemo.rest;

import java.time.LocalDateTime;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	
	private EmployeeService employeeService;
	
	
	@Autowired
	public EmployeeRestController(EmployeeService theemployeeService) {
	
	
		employeeService=theemployeeService;
	}


	
	@GetMapping("/employees")
		public List<Employee> findAll(){
		return employeeService.findAll();
	}
	
	// add mapping for GET employees/{employeeId}
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {
	
	 Employee theEmployee=employeeService.findById(employeeId);
	 
	 if(theEmployee==null) {
		 throw new RuntimeException("Employee Id id not found- "+employeeId);
	 }
	 
	 return theEmployee;
	
	}
	
	
	
	//adding a mapping post 	/employees - add new  employee
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee) {
		
		System.out.println("baba black sheep ");
		//also just in casethey pass an id  in json set the id to 0
		// this is force to save a new item.... instead of update
		theEmployee.setId(0);
		
		employeeService.save(theEmployee);
		
		return theEmployee;	
	}
	
	//add mpping or put employees -update existing employee
	
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {
		employeeService.save(theEmployee);
		
		return theEmployee;
	}
	
	
	@GetMapping("/api")
	public String sayHello1() {
		
		return "this code is writtenby pattam vishal kumar";
	}
	
	//add mapping for DELETE/ employees/{employeeid}-delete employee
	
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		Employee tempemployee = employeeService.findById(employeeId);
		
		if(tempemployee == null) {
			throw new RuntimeException("employee id not found:"+employeeId);
		}
		
		employeeService.deleteById(employeeId);
		
		return "deleted employee id :"+employeeId;
	}
	
	@GetMapping("/")
	public String sayHello() {
		
		return "Hello world! time on server is"+LocalDateTime.now();
	}
	
	//exppose a new end point for workout
	
	@GetMapping("/workout")
	public String getDailyWorkout() {
		return "run a hard 5k !, you are the champ vishal,,,,,,,";
	}
	
	@GetMapping("/fortune")
	public String getDailyFortune() {
		return "today is yourlucky day";
	}

}
