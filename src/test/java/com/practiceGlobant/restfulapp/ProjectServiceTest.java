package com.practiceGlobant.restfulapp;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.practiceGlobant.restfulapp.repository.ProjectRepository;
import com.practiceGlobant.restfulapp.service.ProjectServiceImpl;

@DataJpaTest
public class ProjectServiceTest {

	@Mock
	private ProjectRepository projectRepository;
	private AutoCloseable autoCloaseable;
	private ProjectServiceImpl underTest;
	
	@BeforeEach
	void setUp() {
		autoCloaseable = MockitoAnnotations.openMocks(this);
		underTest = new ProjectServiceImpl(projectRepository);
	}
	
	@AfterEach
	void tearDown() throws Exception {
		autoCloaseable.close();
	}
	
	@Test
	void canGetAllProjects(){
		//when
		underTest.findByName(null);
		//then
		verify(projectRepository).findAll();		
	}
	
	@Test
	void canGetProjectsByName(){
		//when
		String name = "Veribet";
		underTest.findByName(name);
		//then
		verify(projectRepository).findByName(name);	
	}
}
