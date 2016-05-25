package com.capgemini.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.model.Employee;
import com.capgemini.repository.EmployeeRepository;
import com.capgemini.service.EmployeeService;

@Service
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
    private EmployeeRepository employeeRepository;

	@Override
	public Employee findOne(Integer id) {
		return employeeRepository.findOne(id);
	}

	@Override
	public List<Employee> findEmployeeByNameAndPesel(String first_Name, String last_Name, String pesel) {
		return employeeRepository.findEmployeeByNameAndPesel(first_Name, last_Name, pesel);
	}
	
	@Override
	public Employee createEmployee(Employee employee) {
		employee.setIdEmployee(null);
		return employeeRepository.save(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		if(employee.getIdEmployee().equals(null)){
			return null;
		}
		return employeeRepository.save(employee);
	}

	@Override
	public void deleteEmployee(Employee employee) {
		employeeRepository.delete(employee);		
	}

}
