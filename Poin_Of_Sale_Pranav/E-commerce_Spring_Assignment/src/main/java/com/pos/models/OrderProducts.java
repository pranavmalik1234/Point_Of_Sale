package com.pos.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
public class OrderProducts {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderedproductid", unique = true, nullable = false)
	private int orderedproductid;
	
	private String orderid;
	private String productname;
	private int productprice;
	private int productquantity;
	private  int productid;
	public int getOrderedproductid() {
		return orderedproductid;
	}
	public void setOrderedproductid(int orderedproductid) {
		this.orderedproductid = orderedproductid;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public int getProductprice() {
		return productprice;
	}
	public void setProductprice(int productprice) {
		this.productprice = productprice;
	}
	public int getProductquantity() {
		return productquantity;
	}
	public void setProductquantity(int productquantity) {
		this.productquantity = productquantity;
	}
	public int getProductcode() {
		return productid;
	}
	public void setProductcode(int productcode) {
		this.productid = productcode;
	}
	
	
	
	

}
