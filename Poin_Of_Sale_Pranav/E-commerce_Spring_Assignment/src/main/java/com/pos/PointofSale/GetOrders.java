package com.pos.PointofSale;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.pos.constants.Constants;
import com.pos.models.CheckOutDetails;
import com.pos.models.OrderProducts;
import com.pos.models.PlacedOrder;
import com.pos.models.SaveProducts;
import com.pos.models.SavedOrder;
import com.pos.servicesImpl.OrderedProductServiceImpl;
import com.pos.servicesImpl.PlaceOrderServiceImpl;
import com.pos.servicesImpl.SaveOrderServiceImpl;
import com.pos.servicesImpl.SavedProductServiceImpl;



@Path("/getorders")
@Component
public class GetOrders {	

	@Path("/placed")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getplacedOrders(final String json) {
		Gson gs = new Gson();
		List<PlacedOrder> orderList=Constants.context.getBean(PlaceOrderServiceImpl.class).getOrdersEmployee(gs.fromJson(json, String[].class)[0]);
		return new Gson().toJson(orderList);
	}

	@Path("/placedbyid")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getOrderById(final String json) {
		Gson gs = new Gson();
		String orderid=gs.fromJson(json, String[].class)[0];
		List<OrderProducts>orderedProducts=Constants.context.getBean(OrderedProductServiceImpl.class).getOrderbyId(orderid);
		return new Gson().toJson(orderedProducts);
//		return "hello";
		
	}
	
	
	@Path("/saved")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getsaved(final String json) {
		Gson gs = new Gson();
		System.out.println(gs.fromJson(json, String[].class)[0]);
		List<SavedOrder> saveList=Constants.context.getBean(SaveOrderServiceImpl.class).getOrdersEmployee(gs.fromJson(json, String[].class)[0]);
		return new Gson().toJson(saveList);
	}
	
	@Path("/savedbyid")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getSavedOrdersById(final String json) {
		Gson gs = new Gson();
		String orderid=gs.fromJson(json, String[].class)[0];
		List<SaveProducts>savedProducts=Constants.context.getBean(SavedProductServiceImpl.class).getOrderbyId(orderid);
		return new Gson().toJson(savedProducts);
	}

}

