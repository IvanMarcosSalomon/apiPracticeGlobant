package com.despegar.restfulapp.restfulwebservices.loan;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.despegar.restfulapp.restfulwebservices.exceptions.PageNotFoundException;
import com.despegar.restfulapp.restfulwebservices.exceptions.UserNotFoundException;
import com.despegar.restfulapp.restfulwebservices.user.User;
import com.despegar.restfulapp.restfulwebservices.user.UserRepository;

@RestController
public class LoanController {
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/loans")
	public ArrayList<Map<String, ?>> loansOfUser(@RequestParam Integer page, @RequestParam int size, @RequestParam(required = false) Integer user_id) throws PageNotFoundException{
		Pageable pageable = PageRequest.of((page-1), size);
		List<Loan> loans;
		int totalPages;
		
		if(user_id==null) {
			Page<Loan> pageLoans = loanRepository.findAll(pageable);
			totalPages = pageLoans.getTotalPages();
			loans = pageLoans.getContent();
		}
		else {
			Optional<User> userOptional = userRepository.findById(user_id);
			if(!userOptional.isPresent())
				throw new UserNotFoundException("id-" + user_id);
			PagedListHolder<Loan> pageHolder =new PagedListHolder<Loan>(userOptional.get().getLoan());
			pageHolder.setPageSize(size);
			pageHolder.setPage(page-1);
			totalPages = pageHolder.getPageCount();
			loans = pageHolder.getPageList();
		}
		
		if(page > totalPages)
			throw new PageNotFoundException("The page " + page + " does not exist");
		
		Map<String, List<Loan>> items = new TreeMap<>();
		items.put("Items", loans);
		
		JSONObject pagingInfo=new JSONObject();    
		pagingInfo.put("Page",page);    
		pagingInfo.put("Size",size);
		pagingInfo.put("Total",totalPages);
		Map<String, JSONObject> paging = new TreeMap<>();
		paging.put("Paging", pagingInfo);
		
		ArrayList<Map<String, ?>> outputPage = new ArrayList<Map<String, ?>>();
		outputPage.add(items);
		outputPage.add(paging);
		
		return outputPage;
	}

}
