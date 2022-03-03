package com.practiceGlobant.restfulapp.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.practiceGlobant.restfulapp.dto.ProjectDTO;
import com.practiceGlobant.restfulapp.exceptions.ProjectNotFoundException;
import com.practiceGlobant.restfulapp.model.Project;
import com.practiceGlobant.restfulapp.repository.ProjectRepository;
import com.practiceGlobant.restfulapp.service.ProjectService;

import org.springframework.http.ResponseEntity;


@RestController
public class ProjectController {
	
	private final ProjectService projectService;
	
	//no es necesario el autorwired porque solo hay un constructor, a partir de spring 5 no es obligatorio siempre y cuando haya un solo constructor
	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	@GetMapping("/projects")
	public List<ProjectDTO> retrieveAllProjects(@RequestParam(required = false) String name) {
		List<ProjectDTO> projects = projectService.findByName(name);
		return projects;
	}
	
	@GetMapping("/projects/{id}")
	public Optional<ProjectDTO> retrieveProject(@PathVariable int id) {
		Optional<ProjectDTO> project = projectService.findById(id);
		return project;
	}
	
	@PostMapping("/projects")
	public ResponseEntity<Object> createProject(@RequestBody Project project) {
		
		List<Project> projects = projectService.findAll();
		Integer lastProjectId = 0;
		for(Project u:projects) {
			lastProjectId = u.getId();
		}
		project.setId(lastProjectId+1);
		Project savedProject = projectService.save(project);
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedProject.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	/*
	@PutMapping("/projects/{id}")
	public Optional<Project> modifyProject(@PathVariable int id, @RequestBody Project newProject) {
		Optional<Project> targetProject = Optional.of(retrieveProject(id));
		return targetProject
			      .map(project -> {
			    	  project.setName(newProject.getName());
			    	  project.setDescription(newProject.getDescription());
			    	  return projectService.save(project);
			      });
	}
	
	@DeleteMapping("/projects/{id}")
	public void deleteProject(@PathVariable int id) {
		projectService.deleteById(id);
	}
	*/
}
