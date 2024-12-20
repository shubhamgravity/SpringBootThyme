package com.gravity.sbt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gravity.sbt.entity.Customer;
import com.gravity.sbt.repository.CustomerRepository;



@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository custRepo;

	public void addCustomer(Customer customer) {
		custRepo.save(customer);
		
	}

	public List<Customer> showCustomerList() {
		return custRepo.findAll();
		
	}

	public void deleteCustomer(int cno) {
		custRepo.deleteById(cno);	
	}

	public Customer getCustomer(int cno) {
		return custRepo.findById(cno).get();
	}

	public void updateCustomer(int cno, Customer customer) {
		Customer customerFromDb=getCustomer(cno);
		customerFromDb.setEmail(customer.getEmail());
		customerFromDb.setFirstName(customer.getFirstName());
		customerFromDb.setLastName(customer.getLastName());
		customerFromDb.setPhone(customer.getPhone());
		custRepo.save(customerFromDb);
		
		
	}

}
