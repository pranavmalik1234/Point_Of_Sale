package com.pos.PointofSale;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.pos.constants.Constants;
import com.pos.models.Customer;
import com.pos.servicesImpl.CustomersServiceImpl;
import com.pos.servicesImpl.EmployeeServiceImpl;

@Path("/customers")
public class CustomerController {
	
	@Path("/getAll")
    @GET    
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllCustomers() {
		List<Customer> allCustomers=Constants.context.getBean(CustomersServiceImpl.class).getAllCustomers();
		return new Gson().toJson(allCustomers);
    }	   
}
