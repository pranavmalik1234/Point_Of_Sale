package com.pos.DAO;

import java.util.ArrayList;
import java.util.List;

import com.pos.models.SavedOrder;

public interface SaveOrderDao {
	
	public boolean saveOrder(ArrayList<Object> orderDetails);
	public void addOrder(SavedOrder newOrder,String neworderid);
	public List<SavedOrder> getOrderDetail(String employeeName) ;
	public void deleteSavedOrder(String orderid);	

}
