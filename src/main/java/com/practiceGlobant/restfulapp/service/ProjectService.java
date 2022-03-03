package com.practiceGlobant.restfulapp.service;

import java.util.List;
import java.util.Optional;

import com.practiceGlobant.restfulapp.dto.ProjectDTO;
import com.practiceGlobant.restfulapp.model.Project;

public interface ProjectService {

	List<ProjectDTO> findByName(String name);

	Optional<ProjectDTO> findById(int id);

	//void deleteById(int id);
	
	Project save(Project project);

	List<Project> findAll();

}
