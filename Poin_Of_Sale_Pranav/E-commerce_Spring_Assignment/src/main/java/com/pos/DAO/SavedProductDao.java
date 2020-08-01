package com.pos.DAO;

import java.util.ArrayList;
import java.util.List;

import com.pos.models.SaveProducts;

public interface SavedProductDao {
	
	public void addProducts(ArrayList<SaveProducts> savedProductsList);
	public List<SaveProducts> getProductsbyId(String orderid);
	public void deleteSavedProducts(String orderid);

}
