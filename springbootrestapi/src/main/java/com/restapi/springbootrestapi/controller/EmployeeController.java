package com.restapi.springbootrestapi.controller;

import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.springbootrestapi.dao.EmployeeDAO;
import com.restapi.springbootrestapi.model.Employee;

@RestController
@RequestMapping("/company")
public class EmployeeController{
	
	@Autowired
	EmployeeDAO employeeDAO;
	@PostMapping("/employees")
	public Employee CreateEmployee(@Valid @RequestBody Employee emp) {
		return employeeDAO.save(emp);
	}
	
	@GetMapping("/employees")
	public List<Employee>getAllEmployee(){
		return employeeDAO.findAll();
	}
	
	@GetMapping("/notes/{id}")
	public ResponseEntity<Employee> getEmployeeId(@PathVariable(value= "id")Long empid){
		Employee emp=employeeDAO.findOne(empid);
		if(emp==null) {
			return ResponseEntity.notFound().build();
		}
			return ResponseEntity.ok().body(emp);
		}
		
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value= "id")Long empid,@Valid @RequestBody Employee empDetails){
		
		Employee emp=employeeDAO.findOne(empid);
		if(emp==null) {
			return ResponseEntity.notFound().build();
		}
		emp.setName(empDetails.getName());
		emp.setDesignation(empDetails.getDesignation());
		emp.setExpertise(empDetails.getExpertise());
		Employee updateEmployee=employeeDAO.save(emp);
		return ResponseEntity.ok().body(emp);
		
	}
	@DeleteMapping("/notes/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable(value= "id")Long empid){
		Employee emp=employeeDAO.findOne(empid);
		if(emp==null) {
			return ResponseEntity.notFound().build();
		}
		employeeDAO.delete(emp);
		return ResponseEntity.ok().build();
	}
	}


