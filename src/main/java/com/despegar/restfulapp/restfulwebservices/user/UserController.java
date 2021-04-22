package com.despegar.restfulapp.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.despegar.restfulapp.restfulwebservices.exceptions.UserNotFoundException;
import com.despegar.restfulapp.restfulwebservices.loan.Loan;
import com.despegar.restfulapp.restfulwebservices.loan.LoanRepository;

import org.springframework.http.ResponseEntity;


@RestController
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LoanRepository loanRepsitory;
	
	@GetMapping("/users/{id}")
	public Optional<User> retrieveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent())
			throw new UserNotFoundException("id-" + id);
		return user;
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		List<User> users = (List<User>) userRepository.findAll();
		Integer lastUserId = 0;
		for(User u:users) {
			lastUserId = u.getId();
		}
		user.setId(lastUserId+1);
		User savedUser = userRepository.save(user);
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		Optional<User> userOptional = userRepository.findById(id);
		List<Loan> loans = userOptional.get().getLoan();
		for(Loan l:loans){
			loanRepsitory.deleteById(l.getId());
		}
		userRepository.deleteById(id);
	}
	
	
}
