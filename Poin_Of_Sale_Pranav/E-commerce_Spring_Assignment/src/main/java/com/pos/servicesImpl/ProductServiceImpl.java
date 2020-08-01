package com.pos.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos.DAO.ProductDao;
import com.pos.models.Product;
import com.pos.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductDao productdao;

	@Override
	public List<Product> getAllProducts() {
		return productdao.getAllProduct();
	}

}
