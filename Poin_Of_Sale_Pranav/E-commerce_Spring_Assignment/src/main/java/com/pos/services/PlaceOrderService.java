package com.pos.services;

import java.util.ArrayList;
import java.util.List;

import com.pos.models.PlacedOrder;

public interface PlaceOrderService {
	
	public String placeOrder(ArrayList<Object> orderDetails);
	public PlacedOrder setOrder(ArrayList<Object> orderDetails,String neworderid);
	
	public List<PlacedOrder> getOrdersEmployee(String employeeName);
	
	

}
