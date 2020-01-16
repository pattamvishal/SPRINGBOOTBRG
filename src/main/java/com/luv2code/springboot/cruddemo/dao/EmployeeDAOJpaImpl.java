package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.Employee;


@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

	
	private EntityManager entitymanager;
	
	
	EmployeeDAOJpaImpl(EntityManager theEntityManager){
	
		entitymanager=theEntityManager;
	}
	
	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		
		// create query
		
		Query theQuery = entitymanager.createQuery("From Employee");
		
		
		List<Employee> employees= theQuery.getResultList();
		
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		// TODO Auto-generated method stub
		
		
		//get employee
		Employee theEmployee = entitymanager.find(Employee.class,theId);
		
		//return employee
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		// TODO Auto-generated method stub
		
		// save or update the employee
		
		Employee dbEmployee = entitymanager.merge(theEmployee);
		
		theEmployee.setId(dbEmployee.getId());

	}

	@Override
	public void deleteById(int theId) {
		// TODO Auto-generated method stub
		
		// delete the object with primary key
				Query  theQuery = entitymanager.createQuery("delete from Employee where id=:employeeId");
				
				theQuery.setParameter("employeeId", theId);
				
				
				// execute the query
				theQuery.executeUpdate();

	}

}
