package com.cg.movie.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "customer_table")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long customerId;
	private String customerName;
	private String customerPassword;
	private Long customerContact;
	@Pattern(regexp = "Male|Female|Others")
	private String customerGender;
	private double customerBalance;

	@JsonIgnore
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private Set<Ticket> ticketsList = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="customer", cascade= CascadeType.ALL)
	private List<Transaction> transactionList= new ArrayList<Transaction>();

	public Customer() {
		super();
	}

	public Customer(Long customerId, String customerName, String customerPassword, Long customerContact,
			@Pattern(regexp = "Male|Female|Others") String customerGender, int customerBalance) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerPassword = customerPassword;
		this.customerContact = customerContact;
		this.customerGender = customerGender;
		this.customerBalance = customerBalance;
	}
	
	

	@JsonIgnore
	public List<Transaction> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(List<Transaction> transactionList) {
		this.transactionList = transactionList;
	}

	public double getCustomerBalance() {
		return customerBalance;
	}

	public void setCustomerBalance(double d) {
		this.customerBalance = d;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public Long getCustomerContact() {
		return customerContact;
	}

	public void setCustomerContact(Long customerContact) {
		this.customerContact = customerContact;
	}

	public String getCustomerGender() {
		return customerGender;
	}

	public void setCustomerGender(String customerGender) {
		this.customerGender = customerGender;
	}

	@JsonIgnore
	public Set<Ticket> getTicketsList() {
		return ticketsList;
	}

	public void setTicketsList(Set<Ticket> ticketsList) {
		this.ticketsList = ticketsList;
	}

	// the method below will add ticket to customer
	// also serves the purpose to avoid cyclic references.
	public void addTicket(Ticket ticket) {
		ticket.setCustomer(this); // this will avoid nested cascade
		this.getTicketsList().add(ticket);
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerPassword="
				+ customerPassword + ", customerContact=" + customerContact + ", customerGender=" + customerGender
				+ ", customerBalance=" + customerBalance + "]";
	}

}
