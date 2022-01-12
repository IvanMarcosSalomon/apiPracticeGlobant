package com.practiceGlobant.restfulapp.dto;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.practiceGlobant.restfulapp.controller.ProjectController;
import com.practiceGlobant.restfulapp.exceptions.ProjectNotFoundException;
import com.practiceGlobant.restfulapp.model.Project;
import com.practiceGlobant.restfulapp.repository.ProjectRepository;

public class ProjectResponse {
	
	private ProjectController projectController = new ProjectController(null);
	
	private ProjectRepository projectRepository = projectController.getProjectRepository();
	
	@GetMapping("/projects")
	public List<Project> retrieveAllProjects(@RequestParam(required = false) String name) {
		List<Project> projects = (List<Project>) projectRepository.findAll();
		if (!(name==null)) {
			List<Project> projectsByName = projects.stream().
					filter(project -> project.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
			if(projectsByName.isEmpty())
				throw new ProjectNotFoundException("There is no project by the name- " + name);
			return projectsByName;
		}
		return projects;
	}
	
	@GetMapping("/projects/{id}")
	public Project retrieveProject(@PathVariable int id) {
		Optional<Project> project = projectRepository.findById(id);
		if(!project.isPresent())
			throw new ProjectNotFoundException("There is no project with the id-" + id);
		return project.get();
	}
	
	@PostMapping("/projects")
	public ResponseEntity<Object> createProject(@RequestBody Project project) {
		List<Project> projects = (List<Project>) projectRepository.findAll();
		Integer lastProjectId = 0;
		for(Project u:projects) {
			lastProjectId = u.getId();
		}
		project.setId(lastProjectId+1);
		Project savedProject = projectRepository.save(project);
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedProject.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/projects/{id}")
	public Optional<Project> modifyProject(@PathVariable int id, @RequestBody Project newProject) {
		Optional<Project> targetProject = Optional.of(retrieveProject(id));
		return targetProject
			      .map(project -> {
			    	  project.setName(newProject.getName());
			    	  project.setDescription(newProject.getDescription());
			    	  return projectRepository.save(project);
			      });
	}
	
	@DeleteMapping("/projects/{id}")
	public void deleteProject(@PathVariable int id) {
		projectRepository.deleteById(id);
	}

}
