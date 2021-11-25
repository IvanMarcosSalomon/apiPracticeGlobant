package com.practiceGlobant.restfulapp.project;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Project {
	
	@Id
	private Integer id;
	
	private String name;
	private String description;	
	
	public Project() {
	}

	public Project(Integer id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
