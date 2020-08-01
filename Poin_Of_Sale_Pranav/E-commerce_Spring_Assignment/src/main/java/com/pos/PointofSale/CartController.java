package com.pos.PointofSale;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.web.bind.annotation.RequestBody;

import com.google.gson.Gson;
import com.pos.constants.Constants;
import com.pos.models.CartItem;
import com.pos.models.CheckOutDetails;
import com.pos.models.SavedOrder;
import com.pos.servicesImpl.CartServiceImpl;
import com.pos.utility.SavedOrderJsonParser;


@Path("/cart")
public class CartController {
	
	@Path("/addtoCart")
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    public String addtoCart(@RequestBody CartItem cartItem) {
		List<CartItem> customerCart=Constants.context.getBean(CartServiceImpl.class).addItemtoCart(cartItem);
		return new Gson().toJson(customerCart);
    } 	   
	
	@Path("/getCart")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String customerCart(@RequestBody CartItem cartItem) {
		List<CartItem> customerCart=Constants.context.getBean(CartServiceImpl.class).getAllCartItems(cartItem);
		return new Gson().toJson(customerCart);
	}
	
	@Path("/deleteItem")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteItemFromCart(@RequestBody CartItem cartItem) {
		Constants.context.getBean(CartServiceImpl.class).deleteCartItem(cartItem);
		return customerCart(cartItem);
	}
	
	@Path("/deleteCart")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteCart(final String json) {
		Gson gs = new Gson();
		String customerName=gs.fromJson(json, String[].class)[0];
		Constants.context.getBean(CartServiceImpl.class).deletCart(customerName);
		return "hello";
	}
	
	@Path("/addSavedOrder")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String addSavedOrderCart(final String json) {
		ArrayList<Object> orderParserresponse=new SavedOrderJsonParser().parse(json);
		Constants.context.getBean(CartServiceImpl.class).addSavedOrders(orderParserresponse);
		return "hello";
	}
	
}