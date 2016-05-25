package com.capgemini.service;

import java.util.List;

import com.capgemini.model.Employee;

public interface EmployeeService {
	 List<Employee> findEmployeeByNameAndPesel(String first_Name, String last_Name, String pesel);
	 Employee findOne(Integer id);
	 
	 Employee createEmployee(Employee employee);
	 Employee updateEmployee(Employee employee);
	 void deleteEmployee(Employee employee);
}
