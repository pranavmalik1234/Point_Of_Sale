package com.pos.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pos.DAO.EmployeeDao;
import com.pos.models.Employee;
import com.pos.services.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeDao employeeDao;

	@Override
	public int loginResponse(Employee e) {
		return employeeDao.find(e).size();
	}

}
