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
import com.pos.services.SaveOrderService;
import com.pos.servicesImpl.OrderedProductServiceImpl;
import com.pos.servicesImpl.PlaceOrderServiceImpl;
import com.pos.servicesImpl.SaveOrderServiceImpl;
import com.pos.servicesImpl.SavedProductServiceImpl;
import com.pos.utility.OrderJsonParser;

@Path("/order")
public class SaveOrder {	
	
	ArrayList<Object> orderDetails = new ArrayList<Object>();
	
	@Path("/saveorder")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String saveOrder(final String json) throws JsonMappingException, JsonProcessingException {
		
		ArrayList<Object> arrli = new ArrayList<Object>();
		ArrayList<Object> orderDetails=new OrderJsonParser().parseObject(json);
		String saveOrderResponse=Constants.context.getBean(SaveOrderServiceImpl.class).saveOrder(orderDetails);
		int cond=Constants.context.getBean(SavedProductServiceImpl.class).addSavedProducts(saveOrderResponse,orderDetails);
		arrli.add(cond);
		arrli.add(saveOrderResponse);
		String jsonResponse = new Gson().toJson(arrli);
		return jsonResponse;
	}

}

