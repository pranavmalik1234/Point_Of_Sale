package com.pos.utility;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.pos.models.CartItem;

public class SavedOrderJsonParser {
	
	public ArrayList<Object>  parse(String json) {
		Gson gs = new Gson();
		JSONArray jsonarray = new JSONArray(json);
		JSONObject orderDetails=jsonarray.getJSONObject(0);

		String customername=orderDetails.getString("customer");
		String employeename=orderDetails.getString("employee");
		String orderid=orderDetails.getString("orderid");
//		System.out.println(jsonarray.getJSONArray(1).length());
		ArrayList<CartItem> CartItemList=new ArrayList<CartItem>();
		for(int loop=0;loop<jsonarray.getJSONArray(1).length();loop++) {
			 JSONObject SaveProductsObject =jsonarray.getJSONArray(1).getJSONObject(loop);
			 CartItem cartItem= new CartItem();
			 String productid=String.valueOf(SaveProductsObject.getInt("productid"));
			 String productname=SaveProductsObject.getString("productname");
			 int productprice=SaveProductsObject.getInt("productprice");
			 cartItem.setCustomername(customername);
			 cartItem.setEmployeename(employeename);
			 cartItem.setProductid(productid);
			 cartItem.setProductname(productname);
			 cartItem.setProductprice(productprice);
			 CartItemList.add(cartItem);
		}
		ArrayList<Object> orderParserresponse= new ArrayList<Object>();
		orderParserresponse.add(orderid);
		orderParserresponse.add( CartItemList);
		return orderParserresponse;
	}
	
}
