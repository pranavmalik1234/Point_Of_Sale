package com.pos.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos.DAO.CustomerDao;
import com.pos.models.Customer;
import com.pos.services.CustomerService;

@Service
public class CustomersServiceImpl implements CustomerService{
	
	@Autowired
	CustomerDao customerDao;

	@Override
	public List<Customer> getAllCustomers() {
		return customerDao.getAllCustomers();
	}

}
