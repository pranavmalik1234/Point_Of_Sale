package com.pos.DAO;

import java.util.List;

import com.pos.models.Customer;

public interface CustomerDao {
	public List<Customer> getAllCustomers();
	public void CreateCustomer();

}
