package com.practiceGlobant.restfulapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.practiceGlobant.restfulapp.dto.ProjectDTO;
import com.practiceGlobant.restfulapp.exceptions.ProjectNotFoundException;
import com.practiceGlobant.restfulapp.model.Project;
import com.practiceGlobant.restfulapp.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {
	
	private final ProjectRepository projectRepository;
	
	public ProjectServiceImpl(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

	@Override
	public List<ProjectDTO> findByName(String name) {
		
		final List<ProjectDTO> projectDTOs = new ArrayList<>();
		
		if(name == null) {
			projectRepository.findAll().forEach(project->{
				ProjectDTO projectDTO = new ProjectDTO();
				projectDTO.setId(project.getId());
				projectDTO.setName(project.getName());
				projectDTO.setDescription(project.getDescription());
				projectDTOs.add(projectDTO);
			});
		}else {
			projectRepository.findByName(name).forEach(project->{
				ProjectDTO projectDTO = new ProjectDTO();
				projectDTO.setId(project.getId());
				projectDTO.setName(project.getName());
				projectDTO.setDescription(project.getDescription());
				projectDTOs.add(projectDTO);
			});
		}
		
		return projectDTOs;
	}

	@Override
	public Optional<ProjectDTO> findById(int id) {
		final ProjectDTO projectDTO = new ProjectDTO();
		Project project = projectRepository.findById(id);
		if(project == null)
			throw new ProjectNotFoundException("There is no project with the id-" + id);
		projectDTO.setId(project.getId());
		projectDTO.setName(project.getName());
		projectDTO.setDescription(project.getDescription());
		return Optional.of(projectDTO);
	}
	/*
	@Override
	public void deleteById(int id) {
		return projectRepository.deleteById(id);
	}
	*/
	@Override
	public Project save(Project project) {
		return projectRepository.save(project);
	}

	@Override
	public List<Project> findAll() {
		return (List<Project>) projectRepository.findAll();
	}
	
	

}
