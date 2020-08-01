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
import com.pos.models.Employee;
import com.pos.servicesImpl.DrawerServiceImpl;
import com.pos.servicesImpl.EmployeeServiceImpl;
import com.pos.servicesImpl.OrderedProductServiceImpl;
import com.pos.servicesImpl.PlaceOrderServiceImpl;
import com.pos.utility.OrderJsonParser;

@Path("/order")
@Component
public class LogoutController {	
	
	ArrayList<Object> orderDetails = new ArrayList<Object>();
	
	@Path("/placeorder1")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String logout(final String json) throws JsonMappingException, JsonProcessingException {
//		fromJson(json, String[].class)[0];
		Gson gs = new Gson();
		String  employeeName=gs.fromJson(json, String[].class)[0];
		Employee emp=new Employee();
		emp.setUsername(employeeName);
		int amount=Constants.context.getBean(DrawerServiceImpl.class).getEmployeeDrawer(emp);
		System.out.println("amount is");
		System.out.println(amount);
		return new Gson().toJson(amount);
	}

}

