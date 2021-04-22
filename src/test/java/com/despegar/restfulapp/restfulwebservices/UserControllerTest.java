package com.despegar.restfulapp.restfulwebservices;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.despegar.restfulapp.restfulwebservices.loan.LoanRepository;
import com.despegar.restfulapp.restfulwebservices.user.User;
import com.despegar.restfulapp.restfulwebservices.user.UserController;
import com.despegar.restfulapp.restfulwebservices.user.UserRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
@WithMockUser
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	private LoanRepository loanRepository;

	Optional<User> mockUser = Optional.of(new User(1000, "ivan@mail.com","Ivan", "Salomon"));
	
	String userExample = "{\"email\":\"ivan@mail.com\",\"firstName\":\"Ivan\",\"lastName\":\"Salomon\"}";

	@Test
	public void retrieveUser() throws Exception {

		Mockito.when(
				userRepository.findById(Mockito.anyInt())).thenReturn(mockUser);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/users/1000").accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{id:1000,email:ivan@mail.com,firstName:Ivan,lastName:Salomon}";

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
	
	@Test
	public void createUser() throws Exception {
		
		User mockUser = new User(200, "ivan@mail.com","Ivan", "Salomon");

		Mockito.when(
				userRepository.save(Mockito.any(User.class))).thenReturn(mockUser);

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/users")
				.accept(MediaType.APPLICATION_JSON).content(userExample)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());

		assertEquals("http://localhost/users/200",
				response.getHeader(HttpHeaders.LOCATION));

	}
	
	@Test
	public void deleteUser() throws Exception {
		User user = new User(200, "ivan@mail.com","Ivan", "Salomon");
		userRepository.save(user);
		userRepository.deleteById(user.getId());
	    Optional<User> userOptional = userRepository.findById(user.getId());
	    assertEquals(Optional.empty(), userOptional);
	}

}
