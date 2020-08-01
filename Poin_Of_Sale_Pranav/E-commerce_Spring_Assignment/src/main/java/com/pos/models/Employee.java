package com.pos.models;

import javax.persistence.Entity;
import javax.persistence.Id;

//@Entity(name="EMP")
@Entity(name="employee")
public class Employee {
	
//	public int drwawerAmount;
//	public int getDrwawerAmount() {
//		return drwawerAmount;
//	}
//	public void setDrwawerAmount(int drwawerAmount) {
//		this.drwawerAmount = drwawerAmount;
//	}
//	
	
	
	

	@Id
	public String username;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String password;


}
