package com.despegar.restfulapp.restfulwebservices.loan;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends PagingAndSortingRepository<Loan, Integer>{
	
	

}
