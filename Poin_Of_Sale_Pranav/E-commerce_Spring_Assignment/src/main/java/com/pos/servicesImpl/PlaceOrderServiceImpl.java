package com.pos.servicesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos.DAO.PlaceOrderDao;
import com.pos.constants.Constants;
import com.pos.models.CheckOutDetails;
import com.pos.models.Employee;
import com.pos.models.OrderProducts;
import com.pos.models.PlacedOrder;
import com.pos.services.PlaceOrderService;
import com.pos.utility.DateTimeParser;
import com.pos.utility.OrderIdGenerator;



@Service
public class PlaceOrderServiceImpl implements PlaceOrderService{
	
	@Autowired
	PlaceOrderDao placeOrderDao;
	
	@Autowired
	PlacedOrder newOrder;
	@Autowired
	DateTimeParser dateTimeParser;
	@Autowired
	OrderIdGenerator orderid;
	
	@Autowired
	DrawerServiceImpl drawerServiceImp;
	
	@Autowired
	CartServiceImpl cartServiceImpl;
	

	@Override
	public String placeOrder(ArrayList<Object> orderDetails) {
		String palceOrderResponse="";
		
		boolean orderStatus=placeOrderDao.placeOrder(orderDetails);
		palceOrderResponse=String.valueOf(orderStatus); 
		if(orderStatus) {			
			CheckOutDetails temp= (CheckOutDetails) orderDetails.get(0);
			String emplname=temp.getEmployee();
			String customerName=temp.getCustomer();
			String neworderid="#"+orderid.getOrderId()+emplname;
			palceOrderResponse+= neworderid;
			placeOrderDao.addOrder(setOrder(orderDetails, neworderid),neworderid);
			Employee emp=new Employee();
			emp.setUsername(emplname);
			int amount=drawerServiceImp.getEmployeeDrawer(emp);
			drawerServiceImp.setEmployeeDrawer(emp,amount+temp.getSubTotal());
			cartServiceImpl.deletCart(customerName);
			
		}		
		return palceOrderResponse ;
	}

	@Override
	public PlacedOrder setOrder(ArrayList<Object> orderDetails, String neworderid) {
		CheckOutDetails checkoutDetails= (CheckOutDetails) orderDetails.get(0);
		newOrder.setOrderid(neworderid);
		newOrder.setEmployee(checkoutDetails.getEmployee());
		newOrder.setCustomer(checkoutDetails.getCustomer());
		newOrder.setTotalprice(checkoutDetails.getSubTotal());
		newOrder.setOrderStatues("completed");
		newOrder.setDateoforder(dateTimeParser.getDate());
//		newOrder.setDateoforder("2019-08-10");
		newOrder.setTimeoforder(dateTimeParser.getTime());
		newOrder.setPaymentmode(checkoutDetails.getPaymentMode());		
		return newOrder;
	}

	@Override
	public List<PlacedOrder> getOrdersEmployee(String employeeName) {
		List<PlacedOrder> orderList=placeOrderDao.getOrderDetail(employeeName);
		return orderList;
	}


	
	

}
