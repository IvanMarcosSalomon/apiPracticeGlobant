package com.practiceGlobant.restfulapp.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.practiceGlobant.restfulapp.model.Project;

@Repository
public interface ProjectRepository extends PagingAndSortingRepository<Project, Integer>{
	
	
	
}
