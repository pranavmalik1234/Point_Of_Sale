package com.pos.services;

import java.util.ArrayList;
import java.util.List;

import com.pos.models.CartItem;

public interface CartService {
	
	public List<CartItem> addItemtoCart(CartItem cartItem);
	public List<CartItem> getAllCartItems(CartItem cartItem);
	public void deleteCartItem(CartItem cartItem);
	public void deletCart(String customerName);
	
	public void addSavedOrders(ArrayList<Object> orderParserresponse);

}
