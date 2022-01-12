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

import com.practiceGlobant.restfulapp.exceptions.ProjectNotFoundException;
import com.practiceGlobant.restfulapp.model.Project;
import com.practiceGlobant.restfulapp.repository.ProjectRepository;

import org.springframework.http.ResponseEntity;


@RestController
public class ProjectController {
	
	private ProjectRepository projectRepository;
	
	@Autowired
	public ProjectController(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}
	
	@Autowired
	public ProjectRepository getProjectRepository() {
		return projectRepository;
	}

	@Autowired
	public void setProjectRepository(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}
	
	
}
