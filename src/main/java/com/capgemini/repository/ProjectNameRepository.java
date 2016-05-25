package com.capgemini.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.model.ProjectName;

@Transactional(readOnly = true)
public interface ProjectNameRepository extends JpaRepository<ProjectName, Integer> {

	@Query("select projectName from ProjectName projectName where projectName.idProject_Name like :idProject_Name")
	ProjectName findProjectNameById(@Param("idProject_Name") Integer idProject_Name);

	@Query("select projectName from ProjectName projectName join projectName.employee employee join projectName.project project where employee.pesel like :employee and project.project_Name like :project and projectName.role like :role")
	List<ProjectName> findProjectNameByEmployeeProjectRole(@Param("employee") String employee,
			@Param("project") String project, @Param("role") String role);
}
