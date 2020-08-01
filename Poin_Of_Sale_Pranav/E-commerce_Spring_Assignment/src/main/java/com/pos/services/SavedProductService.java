package com.pos.services;

import java.util.ArrayList;
import java.util.List;

import com.pos.models.SaveProducts;

public interface SavedProductService {
	
	public int addSavedProducts(String saveOrderResponse, ArrayList<Object> orderDetails);
	public List<SaveProducts> getOrderbyId(String Orderid);
	public void deleteSavedProducts(String orderid);

}
