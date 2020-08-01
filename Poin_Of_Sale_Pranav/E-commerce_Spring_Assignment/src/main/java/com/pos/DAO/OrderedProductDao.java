package com.pos.DAO;

import java.util.ArrayList;
import java.util.List;

import com.pos.models.CustomerCart;
import com.pos.models.OrderProducts;

public interface OrderedProductDao {
	
	public void addProducts(ArrayList<OrderProducts> orderedProductsList);
	public List<OrderProducts> getProductsbyId(String orderid);
	public List<OrderProducts> getAllOrderedProducts();

}
