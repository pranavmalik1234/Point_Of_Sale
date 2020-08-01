package com.pos.services;

import java.util.ArrayList;
import java.util.List;

import com.pos.models.SavedOrder;

public interface SaveOrderService {
	public String saveOrder(ArrayList<Object> orderDetails);
	public SavedOrder setOrder(ArrayList<Object> orderDetails,String neworderid);
	
	public List<SavedOrder> getOrdersEmployee(String employeeName);
	public void deleteSavedOrder(String orderid);

}
