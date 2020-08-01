package com.pos.PointofSale;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.pos.constants.Constants;
import com.pos.models.Customer;
import com.pos.models.Product;
import com.pos.services.ProductService;
import com.pos.servicesImpl.CustomersServiceImpl;
import com.pos.servicesImpl.ProductServiceImpl;

@Path("/product")
public class ProductController {
	
	@Path("/getAll")
    @GET    
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getALlProducts() {
		List<Product> allProducts=Constants.context.getBean(ProductServiceImpl.class).getAllProducts();
		return new Gson().toJson(allProducts);
    }
    
	   
}