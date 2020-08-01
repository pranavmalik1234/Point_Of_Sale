package com.pos.models;

public class CustomerCart {
	  
	  private String customername;
	  private String employeename;
	  private int productid;
	  private String productname;
	  private int productprice;;
	  private int cartItemId;
	  
	  
	  
	  /**
	   * Extra Params
	   * @return
	   */
	  private int  itemPrice;
	  private int itemQuantity;
	  
	  public int getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}
	public int getItemQuantity() {
		return itemQuantity;
	}
	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	
	
	
	/**
	   * Gettere setter
	   * @return
	   */
		public String getCustomername() {
			return customername;
		}
		public void setCustomername(String customername) {
			this.customername = customername;
		}
		public String getEmployeename() {
			return employeename;
		}
		public void setEmployeename(String employeename) {
			this.employeename = employeename;
		}
		public int getProductid() {
			return productid;
		}
		public void setProductid(int productid) {
			this.productid = productid;
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
		public int getCartItemId() {
			return cartItemId;
		}
		public void setCartItemId(int cartItemId) {
			this.cartItemId = cartItemId;
		}
	  
	
	  
	  
	  

}
