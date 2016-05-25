package com.capgemini.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the employees database table.
 * 
 */
@Entity
@Table(name="employees")
@NamedQuery(name="Employee.findAll", query="SELECT e FROM Employee e")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idEmployee")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idEmployee;

	@Temporal(TemporalType.DATE)
	private Date birth_Date;

	private String first_Name;

	private String last_Name;

	private String pesel;

	//bi-directional many-to-one association to Department
	@ManyToOne
	@JoinColumn(name="Departments_idDepartment")
	private Department department;

	//bi-directional many-to-one association to ProjectName
	@OneToMany(mappedBy="employee")
	private List<ProjectName> projectNames;

	//bi-directional many-to-one association to Project
	@OneToMany(mappedBy="employee")
	private List<Project> projects;

	public Employee() {
	}
	
	public Employee(Integer idEmployee, String first_Name, String last_Name, String pesel, Date birth_Date, Department department) {
		this.idEmployee = idEmployee;
		this.first_Name = first_Name;
		this.last_Name = last_Name;
		this.pesel = pesel;
		this.birth_Date = birth_Date;
		this.department = department;
	}

	public Integer getIdEmployee() {
		return this.idEmployee;
	}

	public void setIdEmployee(Integer idEmployee) {
		this.idEmployee = idEmployee;
	}

	public Date getBirth_Date() {
		return this.birth_Date;
	}

	public void setBirth_Date(Date birth_Date) {
		this.birth_Date = birth_Date;
	}

	public String getFirst_Name() {
		return this.first_Name;
	}

	public void setFirst_Name(String first_Name) {
		this.first_Name = first_Name;
	}

	public String getLast_Name() {
		return this.last_Name;
	}

	public void setLast_Name(String last_Name) {
		this.last_Name = last_Name;
	}

	public String getPesel() {
		return this.pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<ProjectName> getProjectNames() {
		return this.projectNames;
	}

	public void setProjectNames(List<ProjectName> projectNames) {
		this.projectNames = projectNames;
	}

	public ProjectName addProjectName(ProjectName projectName) {
		getProjectNames().add(projectName);
		projectName.setEmployee(this);

		return projectName;
	}

	public ProjectName removeProjectName(ProjectName projectName) {
		getProjectNames().remove(projectName);
		projectName.setEmployee(null);

		return projectName;
	}

	public List<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Project addProject(Project project) {
		getProjects().add(project);
		project.setEmployee(this);

		return project;
	}

	public Project removeProject(Project project) {
		getProjects().remove(project);
		project.setEmployee(null);

		return project;
	}

}