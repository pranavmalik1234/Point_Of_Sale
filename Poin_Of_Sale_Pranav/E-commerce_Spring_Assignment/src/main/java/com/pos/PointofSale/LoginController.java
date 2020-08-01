package com.pos.PointofSale;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.pos.constants.Constants;
import com.pos.models.Employee;
import com.pos.servicesImpl.DrawerServiceImpl;
import com.pos.servicesImpl.EmployeeServiceImpl;

@Path("/login")
@Component
public class LoginController {	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String login(final String json) {
		Gson gs = new Gson();
		Object jsonObj []= gs.fromJson(json, Object[].class);

//		Friends [] n = gs.fromJson(json, Friends [].class);
		Employee emp=gs.fromJson(jsonObj[0].toString(), Employee.class);
		int loginresponse=Constants.context.getBean(EmployeeServiceImpl.class).loginResponse(emp);
		if(loginresponse==1) {
			int amount=gs.fromJson(jsonObj[1].toString(), int.class);
			Constants.context.getBean(DrawerServiceImpl.class).setEmployeeDrawer(emp,amount);
		}
		return new Gson().toJson(loginresponse);
	}

}
