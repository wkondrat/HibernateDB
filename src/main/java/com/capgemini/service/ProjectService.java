package com.capgemini.service;

import java.util.List;

import com.capgemini.model.Employee;
import com.capgemini.model.Project;
import com.capgemini.model.ProjectName;

public interface ProjectService {
	Project createProject(Project project);
	Project findProjectById(Integer id);
	void deleteProject(Project project);
	ProjectName addEmployeeToProject(ProjectName projectName);
	ProjectName deleteEmployeeFromProject(ProjectName projectName);
	ProjectName findProjectNameById(Integer id);
	List<ProjectName> findProjectNameByEmployeeProjectRole(Employee employee, Project project, String role);
	void deleteProjectName(ProjectName projectName);
	void saveProjectName(ProjectName projectName);
}
