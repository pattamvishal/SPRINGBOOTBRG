package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.entity.Employee;



@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {
	
	//define entity manager

	private EntityManager entitymanager;
	
	
	
	//set up constructor engg
	
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager theentitymanager) {
		
		entitymanager=theentitymanager;
	
	}
	
	


	@Override
	public List<Employee> findAll() {

		// get the hibernate session
		Session currentsession=entitymanager.unwrap(Session.class);
		
		//create aquery
		
		Query<Employee> thequery=currentsession.createQuery("from Employee",Employee.class);
		
		
		//execute the query and get result
		
		List<Employee> employees=thequery.getResultList();
		
		
		//return the result
		return employees;
	}




	@Override
	public Employee findById(int theId) {
		// TODO Auto-generated method stub
		
		
		// get the hibernate session
		Session currentsession=entitymanager.unwrap(Session.class);
		
		//
		Employee theEmployee=currentsession.get(Employee.class,theId);
		
		

				
		
		
		return theEmployee;
	}




	@Override
	public void save(Employee theEmployee) {
		
		
		
		// get the hibernate session
				Session currentsession=entitymanager.unwrap(Session.class);
				
				//
				currentsession.saveOrUpdate(theEmployee);


		
	}




	@Override
	public void deleteById(int theId) {
		// TODO Auto-generated method stub
		
		
		// get the hibernate session
		Session currentsession=entitymanager.unwrap(Session.class);
 
		
		//delete object with primary key
		Query theQuery= currentsession.createQuery("delete from Employee where Id=:employeeId");
		
		theQuery.setParameter("employeeId",theId);
		
		theQuery.executeUpdate();
			
		
	}

}
