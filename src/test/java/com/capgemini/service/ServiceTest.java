package com.capgemini.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.model.Department;
import com.capgemini.model.Employee;
import com.capgemini.model.Project;

import com.capgemini.model.ProjectName;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "ServiceTest-context.xml")
public class ServiceTest {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private ProjectService projectService;

	@Test
	public void testShouldFindEmployeeByNameAndPesel() {
		// given
		final String first_Name = "Wojciech";
		final String last_Name = "Kowalski";
		final String pesel = "80010112345";
		// when
		List<Employee> employee = employeeService.findEmployeeByNameAndPesel(first_Name, last_Name, pesel);
		// then
		assertNotNull(employee);
		assertFalse(employee.isEmpty());
		assertEquals("80010112345", employee.get(0).getPesel());
	}

	@Test
	public void testShouldFindEmployeeById() {
		// given
		final int id = 1;
		// when
		Employee employee = employeeService.findOne(id);
		// then
		assertNotNull(employee);
	}

	@Test
	public void testShouldUpdateEmployee() {
		// given
		final String first_Name = "Marcin";
		final String last_Name = "Kowalski";
		String pesel = "80010112345";
		Date birth_Date = new Date();
		Department department = new Department(1, "Department1");
		Employee employee = new Employee(1, first_Name, last_Name, pesel, birth_Date, department);
		// when
		String employeeFirst_Name = employeeService.findOne(employeeService.updateEmployee(employee).getIdEmployee())
				.getFirst_Name();
		// then
		assertEquals("Marcin", employeeFirst_Name);
		// clean up
		employee.setFirst_Name("Wojciech");
		employee.setBirth_Date(new Date(80, 0, 1));
		employeeService.updateEmployee(employee);
	}

	@Test
	public void testShouldCreateEmployee() {
		// given
		final String first_Name = "Wojciech";
		final String last_Name = "Kowalski";
		final String pesel = "12345678913";
		Date birth_Date = new Date();
		Employee employee = new Employee(null, first_Name, last_Name, pesel, birth_Date, null);
		// when
		employeeService.createEmployee(employee);
		// then
		assertNotNull(employeeService.findOne(employee.getIdEmployee()));
		assertEquals("12345678913", employeeService.findOne(employee.getIdEmployee()).getPesel());
		// clean up
		employeeService.deleteEmployee(employee);
	}
	
	@Ignore
	@Test
	public void testShouldDeleteEmployee() {
		Integer id = 35;
		employeeService.deleteEmployee(employeeService.findOne(id));
		assertNull(employeeService.findOne(id));
	}

	@Test
	public void testShouldCreateProject() {
		// given
		final String project_Name = "Test";
		final String status = "INNER";
		Integer idEmployee = 1;
		Employee employee = employeeService.findOne(idEmployee);
		Project project = new Project(null, project_Name, employee, status);
		// when
		projectService.createProject(project);
		// then
		assertEquals("Test", projectService.findProjectById(project.getIdProject()).getProject_Name());
		// clean up
		projectService.deleteProject(project);
	}

	@Test
	public void shouldAddEmployeeToProject() {
		// given
		Integer idEmployee = 1;
		Employee employee = employeeService.findOne(idEmployee);
		final String project_Name = "Alfa";
		final String status = "INNER";
		final String role = "DEV";
		BigDecimal earnings = new BigDecimal(1500);
		Project project = new Project(1, project_Name, employee, status);
		ProjectName projectName = new ProjectName(null, employee, role, new Date(), null, earnings, project);
		// when
		projectService.addEmployeeToProject(projectName);
		List<ProjectName> projectNames = projectService.findProjectNameByEmployeeProjectRole(employee, project, role);
		// then
		assertFalse(projectNames.isEmpty());
		// clean up
		projectService.deleteProjectName(projectName);
	}

	@Test
	public void shouldRemoveEmployeeFromProject() {
		// given
		Integer id = 48;
		ProjectName projectName = projectService.findProjectNameById(id);
		// when
		assertNull(projectName.getDate_Out());
		projectService.deleteEmployeeFromProject(projectName);
		// then
		assertNotNull(projectName.getDate_Out());
		// clean up
		projectName.setDate_Out(null);
		projectService.saveProjectName(projectName);
	}
}
