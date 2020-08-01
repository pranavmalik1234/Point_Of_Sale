package com.pos.DAO;

import java.util.List;

import com.pos.models.Product;

public interface ProductDao {
	public List<Product> getAllProduct();
	public void CreateProduct();

}
