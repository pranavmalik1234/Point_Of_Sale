package com.pos.DAO;

import java.util.ArrayList;
import java.util.List;

import com.pos.models.PlacedOrder;

public interface PlaceOrderDao  {
	
	public boolean placeOrder(ArrayList<Object> orderDetails);
	
	public void addOrder(PlacedOrder newOrder,String neworderid);
	
	public List<PlacedOrder> getOrderDetail(String employeeName) ;	
	public List<PlacedOrder> getAllOrders();
	public List<PlacedOrder> getAllOrdersReport(String stDate, String edDate);

}
