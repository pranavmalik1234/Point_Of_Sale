package com.pos.services;

import com.pos.models.Employee;

public interface DrawerService {
	
	public void setEmployeeDrawer(Employee emp, int amount);
	
	public int getEmployeeDrawer(Employee emp);

}
