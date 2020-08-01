package com.pos.PointofSale;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;
import com.pos.constants.Constants;
import com.pos.services.PlaceOrderService;
import com.pos.servicesImpl.EmployeeServiceImpl;
import com.pos.servicesImpl.OrderedProductServiceImpl;
import com.pos.servicesImpl.PlaceOrderServiceImpl;
import com.pos.utility.OrderJsonParser;

@Path("/order")
@Component
public class PlaceOrder {	
	
	ArrayList<Object> orderDetails = new ArrayList<Object>();
	
	@Path("/placeorder")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String placeOrder(final String json) throws JsonMappingException, JsonProcessingException {
		
		ArrayList<Object> arrli = new ArrayList<Object>();
		ArrayList<Object> orderDetails=new OrderJsonParser().parseObject(json);
		String placeOrderResponse= Constants.context.getBean(PlaceOrderServiceImpl.class).placeOrder(orderDetails);
		
		int response=Constants.context.getBean(OrderedProductServiceImpl.class).addOrderedProducts(placeOrderResponse,orderDetails);;
		arrli.add(response);
		arrli.add(placeOrderResponse.substring(4));
		String jsonResponse = new Gson().toJson(arrli);
		return jsonResponse;
	}

}

