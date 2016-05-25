package com.capgemini.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the project_name database table.
 * 
 */
@Entity
@Table(name="project_name")
@NamedQuery(name="ProjectName.findAll", query="SELECT p FROM ProjectName p")
public class ProjectName implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idProject_Name")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idProject_Name;

	@Temporal(TemporalType.DATE)
	private Date date_In;

	@Temporal(TemporalType.DATE)
	private Date date_Out;

	private BigDecimal eearnings;

	private String role;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="Employees_idEmployee")
	private Employee employee;

	//bi-directional many-to-one association to Project
	@ManyToOne
	@JoinColumn(name="Projects_idProject")
	private Project project;

	public ProjectName() {
	}
	
	public ProjectName(Integer idProject_Name, Employee employee, String role, Date date_In, Date date_Out, BigDecimal eearnings, Project project) {
		this.idProject_Name = idProject_Name;
		this.employee = employee;
		this.role = role;
		this.date_In = date_In;
		this.date_Out = date_Out;
		this.eearnings = eearnings;
		this.project = project;
	}

	public Integer getIdProject_Name() {
		
		return this.idProject_Name;
	}

	public void setIdProject_Name(Integer idProject_Name) {
		this.idProject_Name = idProject_Name;
	}

	public Date getDate_In() {
		return this.date_In;
	}

	public void setDate_In(Date date_In) {
		this.date_In = date_In;
	}

	public Date getDate_Out() {
		return this.date_Out;
	}

	public void setDate_Out(Date date_Out) {
		this.date_Out = date_Out;
	}

	public BigDecimal getEearnings() {
		return this.eearnings;
	}

	public void setEearnings(BigDecimal eearnings) {
		this.eearnings = eearnings;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}