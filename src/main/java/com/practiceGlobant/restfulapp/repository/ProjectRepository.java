package com.practiceGlobant.restfulapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.practiceGlobant.restfulapp.model.Project;

@Repository
public interface ProjectRepository extends PagingAndSortingRepository<Project, Integer>{
	
	@Query("SELECT p FROM Project p WHERE p.name = ?1")
	public List<Project> findByName(String name);
	
	public Project findById(int id);
	
}
