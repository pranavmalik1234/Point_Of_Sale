package com.pos.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos.DAO.SavedProductDao;
import com.pos.models.CustomerCart;
import com.pos.models.SaveProducts;
import com.pos.services.SavedProductService;

@Service
public class SavedProductServiceImpl implements SavedProductService{
	
	@Autowired
	SavedProductDao savedProductDao;

	@Override
	public int addSavedProducts(String saveOrderResponse, ArrayList<Object> orderDetails) {
		int cond=1;
		CustomerCart [] customerCart= (CustomerCart[]) orderDetails.get(1);
		ArrayList< SaveProducts> savedProductList=new ArrayList< SaveProducts>();
		for(int i=0;i<customerCart.length;i++) {
			CustomerCart c=customerCart[i];
			SaveProducts savedProducts = new SaveProducts();
			savedProducts.setOrderid(saveOrderResponse);
			savedProducts.setProductname(c.getProductname());
			savedProducts.setProductprice(c.getProductprice());
			savedProducts.setProductquantity(c.getItemQuantity());
			savedProducts.setProductcode(c.getProductid());
			savedProductList.add(savedProducts);
//			 
			
		}
		savedProductDao.addProducts(savedProductList);
		return cond;
		
	}
	
	@Override
	public List<SaveProducts> getOrderbyId(String Orderid) {
		List<SaveProducts> savedProducts=savedProductDao.getProductsbyId(Orderid);
		return savedProducts;
	}

	@Override
	public void deleteSavedProducts(String orderid) {
		savedProductDao.deleteSavedProducts(orderid);
		
	}

}
