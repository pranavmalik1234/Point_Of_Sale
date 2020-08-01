package com.pos.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Repository;

@Repository
@Entity
public class SavedOrder {
	

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderid", unique = true, nullable = false)
	private String orderid;
	
	
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	private String employee;
	private String customer;
	private String paymentmode;
	public String getPaymentmode() {
		return paymentmode;
	}
	public void setPaymentmode(String paymentmode) {
		this.paymentmode = paymentmode;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	private int totalprice;
	private String dateoforder;
	private String timeoforder;
	private String orderStatues;
	
	public String getEmployee() {
		return employee;
	}
	public void setEmployee(String employee) {
		this.employee = employee;
	}
	public int getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}
	public String getDateoforder() {
		return dateoforder;
	}
	public void setDateoforder(String dateoforder) {
		this.dateoforder = dateoforder;
	}
	public String getTimeoforder() {
		return timeoforder;
	}
	public void setTimeoforder(String timeoforder) {
		this.timeoforder = timeoforder;
	}
	public String getOrderStatues() {
		return orderStatues;
	}
	public void setOrderStatues(String orderStatues) {
		this.orderStatues = orderStatues;
	}

}
