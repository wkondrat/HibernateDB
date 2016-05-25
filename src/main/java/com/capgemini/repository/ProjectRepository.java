package com.capgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.model.Project;

@Transactional(readOnly = true)
public interface ProjectRepository extends JpaRepository<Project, Integer>{
	
	@Query("select project from Project project where project.id like :id")
	Project findProjectById(@Param("id") Integer id);
}
