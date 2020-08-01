package com.pos.services;

import java.util.ArrayList;
import java.util.List;

import com.pos.models.OrderProducts;

public interface OrderedProductService {
	
	public int addOrderedProducts(String placeOrderResponse, ArrayList<Object> orderDetails);
	public void getProuctsofOrder();
	public List<OrderProducts> getOrderbyId(String Orderid);

}
