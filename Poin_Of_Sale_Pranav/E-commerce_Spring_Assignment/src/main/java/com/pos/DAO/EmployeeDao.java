package com.pos.DAO;

import java.util.List;

import com.pos.models.Employee;

public interface EmployeeDao {
	
	public void CreateEmployee(Employee e);
	public void UpdateEmployee(Employee e);
	public void edit(Employee ee);
	public void DeleteEmployee(Employee e);
	public List<Employee> find(Employee e);
	public void getAllEmployee();

}
