package com.capgemini.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;import static javax.persistence.LockModeType.READ;


/**
 * The persistent class for the projects database table.
 * 
 */
@Entity
@Table(name="projects")
@NamedQuery(name="Project.findAll", query="SELECT p FROM Project p", lockMode = READ)
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idProject")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idProject;

	private String project_Name;

	private String state;

	//bi-directional many-to-one association to ProjectName
	@OneToMany(mappedBy="project")
	private List<ProjectName> projectNames;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="Employees_idEmployee")
	private Employee employee;

	public Project() {
	}
	
	public Project(Integer idProject, String project_Name, Employee employee, String state) {
		this.idProject = idProject;
		this.project_Name = project_Name;
		this.employee = employee;
		this.state = state;
	}

	public Integer getIdProject() {
		return this.idProject;
	}

	public void setIdProject(Integer idProject) {
		this.idProject = idProject;
	}

	public String getProject_Name() {
		return this.project_Name;
	}

	public void setProject_Name(String project_Name) {
		this.project_Name = project_Name;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<ProjectName> getProjectNames() {
		return this.projectNames;
	}

	public void setProjectNames(List<ProjectName> projectNames) {
		this.projectNames = projectNames;
	}

	public ProjectName addProjectName(ProjectName projectName) {
		getProjectNames().add(projectName);
		projectName.setProject(this);

		return projectName;
	}

	public ProjectName removeProjectName(ProjectName projectName) {
		getProjectNames().remove(projectName);
		projectName.setProject(null);

		return projectName;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}