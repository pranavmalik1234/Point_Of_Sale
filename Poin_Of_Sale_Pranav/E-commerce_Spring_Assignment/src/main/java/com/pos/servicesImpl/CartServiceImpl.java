package com.pos.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos.DAO.CartDao;
import com.pos.models.CartItem;
import com.pos.services.CartService;
import com.pos.services.SaveOrderService;
import com.pos.services.SavedProductService;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	CartDao cartDao;
	
	@Autowired
	SaveOrderService saveOrderService;
	
	@Autowired
	SavedProductService savedProductService;

	@Override
	public List<CartItem> addItemtoCart(CartItem cartItem) {
		cartDao.addItemtoCart(cartItem);	
		return cartDao.getAllCartItems(cartItem);
	}

	@Override
	public List<CartItem> getAllCartItems(CartItem cartItem) {
		return cartDao.getAllCartItems(cartItem);
		
	}

	@Override
	public void deleteCartItem(CartItem cartItem) {
		 cartDao.deleteCartItem(cartItem);
	}

	@Override
	public void deletCart(String customerName) {
		if(customerName.length()>=1) {
			CartItem cartItem = new CartItem();
			cartItem.setCustomername(customerName);
			cartDao.deletCart(cartItem);
		}
		
	}
	
	@Override
	public void addSavedOrders(ArrayList<Object> orderParserresponse) {
		String orderid=(String) orderParserresponse.get(0);
		ArrayList<CartItem> CartItemList =(ArrayList<CartItem>) orderParserresponse.get(1);
		this.deletCart(CartItemList.get(0).getCustomername());
		cartDao.addSavedOrders(CartItemList);
		saveOrderService.deleteSavedOrder(orderid);
		savedProductService.deleteSavedProducts(orderid);
		
		
	}

}
