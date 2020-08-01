package com.pos.DAO;

import java.util.ArrayList;
import java.util.List;

import com.pos.models.CartItem;

public interface CartDao {
	
	public void addItemtoCart(CartItem cartItem);
	public List<CartItem> getAllCartItems(CartItem cartItem);
	public void deleteCartItem(CartItem cartItem) ;
	public void deletCart(CartItem cartItem);
	public void addSavedOrders(ArrayList<CartItem> CartItemList);

}
