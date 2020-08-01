package com.pos.drawer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.pos.models.Employee;

@Component
public class DrawerDetails {
	
	private static Map employeeDrawers=new HashMap();

	public int getEmployeeDrawers(Employee emp) {
		int drawerValue=-1;
		 Set set=this.employeeDrawers.entrySet();
		 Iterator itr=set.iterator();  
		    while(itr.hasNext()){  
		        //Converting to Map.Entry so that we can get key and value separately  
		        Map.Entry entry=(Map.Entry)itr.next();  
		        if(entry.getKey().equals(emp.getUsername())) {
		        	drawerValue=(int) entry.getValue();
		        }
		        System.out.println(entry.getKey()+" "+entry.getValue());  
		    }  
		    return drawerValue;
		    
	}

	public void setEmployeeDrawers(Employee emp, int amount) {
		this.employeeDrawers.put(emp.getUsername(), amount);
	}

}
