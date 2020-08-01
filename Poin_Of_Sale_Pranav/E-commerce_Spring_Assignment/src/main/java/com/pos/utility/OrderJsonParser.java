package com.pos.utility;

import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.pos.models.CheckOutDetails;
import com.pos.models.CustomerCart;

public class OrderJsonParser {
	ArrayList<Object> orderDetails = new ArrayList<Object>();
	
	public void parseArray(String json) throws JsonMappingException, JsonProcessingException {
		Gson gs = new Gson();
		Object []o= gs.fromJson(json.toString(), Object[].class);
		System.out.println(o[0].toString());
//		return parseObject(o[0].toString(),o[1].toString());
	}
	
	public ArrayList<Object> parseObject(String json) throws JsonMappingException, JsonProcessingException {
//		System.out.println(json);
		final ObjectNode node = new ObjectMapper().readValue(json, ObjectNode.class);
		JsonNode checkOutCartDetails=node.get("checkOutCartDetails");
		JsonNode CartItemDetails=node.get("CartItemDetails");
		JsonNode itemPrice=node.get("itemPrice");
		JsonNode itemQuantity=node.get("itemQuantity");
		JsonNode paymentMethod=node.get("paymentMethod");
		String paymentMode=paymentMethod.toString();
		paymentMode=paymentMode.substring(1, paymentMode.length()-1);
		
		return parseJsonArray(checkOutCartDetails, CartItemDetails,itemPrice,itemQuantity, paymentMode);
	}
	
	public ArrayList<Object> parseJsonArray(JsonNode checkOutCartDetails, JsonNode CartItemDetails,JsonNode itemPrice,JsonNode itemQuantity,String paymentMode) {
		Gson gs = new Gson();
		CheckOutDetails[] checkoutDetails  = gs.fromJson(checkOutCartDetails.toString(), CheckOutDetails[].class);
		CustomerCart [] customerCart= gs.fromJson(CartItemDetails.toString(), CustomerCart[].class);
		int [] customerItemPrice=gs.fromJson(itemPrice.toString(), int[].class);
		int []customerItemQuantity=gs.fromJson(itemQuantity.toString(), int[].class);
		checkoutDetails[0].setPaymentMode(paymentMode);
		System.out.println(checkoutDetails[0].getCustomer()+"*"+checkoutDetails[0].getEmployee()+"*"+checkoutDetails[0].getPaymentMode());
		for(int i=0;i<customerCart.length;i++) {
			CustomerCart  c= customerCart[i];
			c.setItemPrice(customerItemPrice[i]);
			c.setItemQuantity(customerItemQuantity[i]);
			//System.out.println(c.getCustomername()+"-"+c.getEmployeename()+"-"+c.getProductid()+"-"+c.getProductname()+"-"+c.getProductprice()+"-"+c.getCartItemId()+"==>"+c.getItemPrice()+"-"+c.getItemQuantity());
		}
		
		orderDetails.add(checkoutDetails[0]);
		orderDetails.add(customerCart);
		return orderDetails;
		
	}

}
