package com.pos.models;

public class TotalSalesReport {

	private String employeeName;
	private String customereName;
	private String productName;
	
	private int emloyeeSales;
	private int customerSales;
	private int productSales;
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getCustomereName() {
		return customereName;
	}
	public void setCustomereName(String customereName) {
		this.customereName = customereName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getEmloyeeSales() {
		return emloyeeSales;
	}
	public void setEmloyeeSales(int emloyeeSales) {
		this.emloyeeSales = emloyeeSales;
	}
	public int getCustomerSales() {
		return customerSales;
	}
	public void setCustomerSales(int customerSales) {
		this.customerSales = customerSales;
	}
	public int getProductSales() {
		return productSales;
	}
	public void setProductSales(int productSales) {
		this.productSales = productSales;
	}
	
	
}
