package com.despegar.restfulapp.restfulwebservices.loan;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.despegar.restfulapp.restfulwebservices.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Loan {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private double totalAmount;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Loan [id=" + id + ", totalAmount=" + totalAmount + "]";
	}
	
}
