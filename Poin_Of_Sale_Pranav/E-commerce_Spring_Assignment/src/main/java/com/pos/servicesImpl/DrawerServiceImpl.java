package com.pos.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos.drawer.DrawerDetails;
import com.pos.models.Employee;
import com.pos.services.DrawerService;


@Service
public class DrawerServiceImpl implements DrawerService{
	
	@Autowired
	DrawerDetails drawerDetails;

	@Override
	public void setEmployeeDrawer(Employee emp,int amount) {
		drawerDetails.setEmployeeDrawers(emp,amount);
	}

	@Override
	public int getEmployeeDrawer(Employee emp) {
		return drawerDetails.getEmployeeDrawers(emp);
		
	}

}
