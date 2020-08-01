package com.pos.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos.DAO.OrderedProductDao;
import com.pos.models.CustomerCart;
import com.pos.models.OrderProducts;
import com.pos.services.OrderedProductService;

@Service
public class OrderedProductServiceImpl implements OrderedProductService{
	
	
	@Autowired
	OrderedProductDao orderedProductDao;

	@Override
	public int addOrderedProducts(String placeOrderResponse, ArrayList<Object> orderDetails) {
		int cond=0;
		String [] responses=placeOrderResponse.split("#");
		if("true".equals(responses[0])) {
			cond=1;
			CustomerCart [] customerCart= (CustomerCart[]) orderDetails.get(1);
			ArrayList<OrderProducts> orderedProductsList=new ArrayList<OrderProducts>();
			for(int i=0;i<customerCart.length;i++) {
				CustomerCart c=customerCart[i];
				OrderProducts orderedProduct= new OrderProducts();
				orderedProduct.setOrderid("#"+responses[1]);
				orderedProduct.setProductname(c.getProductname());
				orderedProduct.setProductprice(c.getProductprice());
				orderedProduct.setProductquantity(c.getItemQuantity());
				orderedProduct.setProductcode(c.getProductid());
				orderedProductsList.add(orderedProduct);
//				System.out.println(c.getCustomername()+"-"+c.getEmployeename()+"-"+c.getProductid()+"-"+c.getProductname()+"-"+c.getProductprice()+"-"+c.getCartItemId()+"==>"+c.getItemPrice()+"-"+c.getItemQuantity());
			}
			orderedProductDao.addProducts(orderedProductsList);

		}
		return cond;
		
		
		
	}

	@Override
	public void getProuctsofOrder() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<OrderProducts> getOrderbyId(String Orderid) {
		List<OrderProducts> orderedProducts=orderedProductDao.getProductsbyId(Orderid);
		return orderedProducts;
	}

}
