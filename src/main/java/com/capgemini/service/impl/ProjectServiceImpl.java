package com.capgemini.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.Employee;
import com.capgemini.model.Project;
import com.capgemini.model.ProjectName;
import com.capgemini.repository.EmployeeRepository;
import com.capgemini.repository.ProjectNameRepository;
import com.capgemini.repository.ProjectRepository;
import com.capgemini.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	ProjectNameRepository projectNameRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public Project createProject(Project project) {
		return projectRepository.save(project);
	}

	@Override
	public Project findProjectById(Integer id) {
		return projectRepository.findProjectById(id);
	}

	@Override
	public void deleteProject(Project project) {
		projectRepository.delete(project);
	}

	@Override
	public ProjectName addEmployeeToProject(ProjectName projectName) {
		return projectNameRepository.save(projectName);
	}

	@Override
	public ProjectName deleteEmployeeFromProject(ProjectName projectName) {
		projectName.setDate_Out(new Date());
		return projectNameRepository.save(projectName);
	}

	@Override
	public ProjectName findProjectNameById(Integer id) {
		return projectNameRepository.findProjectNameById(id);
	}

	@Override
	public List<ProjectName> findProjectNameByEmployeeProjectRole(Employee employee, Project project, String role) {
		return projectNameRepository.findProjectNameByEmployeeProjectRole(employee.getPesel(),
				project.getProject_Name(), role);
	}

	@Override
	public void deleteProjectName(ProjectName projectName) {
		projectNameRepository.delete(projectName);
	}

	@Override
	public void saveProjectName(ProjectName projectName) {
		projectNameRepository.save(projectName);
	}

}
