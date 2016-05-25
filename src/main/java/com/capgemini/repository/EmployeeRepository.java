package com.capgemini.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capgemini.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query("select employee from Employee employee where upper(employee.first_Name) like concat(upper(:first_Name), '%') and upper(employee.last_Name) like concat(upper(:last_Name), '%') and upper(employee.pesel) like concat(upper(:pesel), '%')")
	public List<Employee> findEmployeeByNameAndPesel(@Param("first_Name") String first_Name, @Param("last_Name") String last_Name,
			@Param("pesel") String pesel);
	
	@Query("select employee from Employee employee where upper(employee.first_Name) like concat(upper(:first_Name), '%') and upper(employee.last_Name) like concat(upper(:last_Name), '%')")
	public List<Employee> findEmployeeByName(@Param("first_Name") String first_Name, @Param("last_Name") String last_Name);
	
	@Query("select employee from Employee employee where upper(employee.pesel) like concat(upper(:pesel), '%')")
	public List<Employee> findEmployeeByPesel(@Param("pesel") String pesel);
	
}
